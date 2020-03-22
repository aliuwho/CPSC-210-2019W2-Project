package ui.gui;

import model.Chip;
import model.FourBoard;
import model.exceptions.ColumnFullException;
import model.exceptions.EndGameException;
import persistence.Saveable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * A class representing an game of Connect4 against a computer
 */
public class ConnectWindow extends Window implements ActionListener, KeyListener {
    private final Saveable saveable;
    private final FourBoard board;
    private int column = 0;
    //    private JLabel label = createLabel("temp");
    private Image redChip;
    private Image blueChip;
    private Image emptyChip;
    private Image arrow;
    private final int dimension;
    private final JImageComponent[][] components = new JImageComponent[7][7];
    private Color playerColor;
    private Color enemyColor;

    // EFFECTS: creates a new Connect4 game
    public ConnectWindow(Saveable saveable, Color player) {
        super("Connect 4", 1, 1);
        if (getScreenHeight() > getScreenWidth()) {
            dimension = getScreenHeight() * 7 / 10;
        } else {
            dimension = getScreenWidth() * 7 / 10;
        }
        frame.setPreferredSize(new Dimension(dimension, dimension));
        this.saveable = saveable;
        this.board = new FourBoard();
        playerColor = player;
        initEnemy();
        frame.setLayout(new GridLayout(7, 8));
        initImages();
//        label.addKeyListener(this);
//        frame.addKeyListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets enemy color
    public void initEnemy() {
        if (playerColor.equals(Color.RED)) {
            enemyColor = Color.BLUE;
        } else {
            enemyColor = Color.RED;

        }
    }

    // MODIFIES: this
    // EFFECTS: stores chip and arrow images
    private void initImages() {
        try {
            int temp = dimension / 10;
            redChip = getScaledImage(ImageIO.read(new File("./data/red_circle.png")), temp, temp);
            blueChip = getScaledImage(ImageIO.read(new File("./data/blue_circle.png")), temp, temp);
            emptyChip = getScaledImage(ImageIO.read(new File("./data/grey_circle.png")), temp, temp);
            arrow = getScaledImage(ImageIO.read(new File("./data/arrow.png")), temp, temp);
        } catch (IOException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "The game could not be loaded.",
                    "Image Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates an arrow image at the top of each column
    private void createArrows() {
        for (int i = 0; i < components[0].length; i++) {
            JImageComponent arrow = new JImageComponent();
            arrow.setBufferedImage((BufferedImage) this.arrow);
            if (i != 0) {
                arrow.setVisible(false);
            }
            components[0][i] = arrow;
            frame.add(components[0][i]);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes action events
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "quit":
                frame.dispose();
                break;
            case "help":
                JOptionPane.showMessageDialog(frame, "this is the help window");
                break;
        }
        updateBoard(column);
    }

    // EFFECTS: moves column arrow left, if possible
    private int moveLeft() {
        return Math.max(column - 1, 0);
    }

    // EFFECTS: updates column arrow right, if possible
    private int moveRight() {
        return Math.min(column + 1, FourBoard.ROWS);
    }

    // EFFECTS: creates frame to display
    @Override
    protected void createFrame() {
        createArrows();
        createKeyField();
        createBoard();
    }

    // MODIFIES: this
    // EFFECTS: enables player input using arrow keys
    private void createKeyField() {
        JTextField text = new JTextField("Good luck!", 1);
        text.setFont(new Font(Window.FONT_NAME, Window.FONT_TYPE, Window.FONT_SIZE));
        text.addKeyListener(this);
        text.setOpaque(false);
        text.setBorder(null);
        text.setEditable(false);
        frame.add(text);
    }

    // MODIFIES: this
    // EFFECTS: places visual pieces on board
    private void createBoard() {
        int j = 1;
        int k = 0;
        for (int i = 0; i < 48; i++) {
            if (i == 7) {
                frame.add(createButton("How to Play,", "help", this));
            } else if (i == 31 | i == 23 | i == 39 | i == 47) {
                frame.add(new JPanel());
            } else if (i == 15) {
                frame.add(createButton("Quit", "quit", this));
            } else {
                JImageComponent piece = new JImageComponent();
                piece.setBufferedImage((BufferedImage) emptyChip);
                piece.setVisible(true);
                piece.setName("empty");
                frame.add(piece);
                if (k >= components[j].length) {
                    k = 0;
                    j++;
                    //                    j++;
                }
                components[j][k] = piece;
                k++;

            }
        }
    }

    /**
     * taken from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     **/
    // EFFECTS: rescales srcImage to a width of w and height of h
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    // MODIFIES: this
    // EFFECTS: updates column arrow on keypress
    @Override
    public void keyPressed(KeyEvent event) {

//        printEventInfo("Key Pressed", event);
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            updateBoard(moveLeft());
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            updateBoard((moveRight()));
        }

    }

    // MODIFIES: this
    // EFFECTS: runs player and computer moves
    private void playMoves() throws ColumnFullException, EndGameException {
        board.addChip(new Chip(playerColor), column);
        placePiece(playerColor, column);
        computerPlay();
    }

    // MODIFIES: this
    // EFFECTS: changes graphics of lowest empty chip in given column to match color
    private void placePiece(Color color, int col) {
        Image chip;
        if (color.equals(Color.RED)) {
            chip = redChip;
        } else {
            chip = blueChip;
        }
        int i = components.length - 1;
        while (i >= 1 && !components[i][col].getName().equals("empty")) {
            i--;
        }
        if (i != 0) {
            components[i][col].setVisible(false);
            components[i][col].setBufferedImage((BufferedImage) chip);
            components[i][col].setName("filled");
            components[i][col].setVisible(true);

        }
    }

    // MODIFIES: this
    // EFFECTS: creates a move for the computer
    private void computerPlay() throws EndGameException {
        int i = firstMove(tryMoves());
        try {
            board.addChip(new Chip(enemyColor), i);
            placePiece(enemyColor, i);
        } catch (ColumnFullException e) {
            System.out.println("Something failed in computer play");
            e.printStackTrace();
        }
    }

    // EFFECTS: maps viable moves
    private HashMap<Integer, Integer> tryMoves() {
        HashMap<Integer, Integer> options = new HashMap<>();
        options.put(1, -1);
        options.put(2, -1);
        for (int i = 0; i < components[0].length; i++) {
            FourBoard next = new FourBoard();
//            Chip[][] temp = board.getChips().clone();
            Chip[][] temp = cloneChips(board.getChips());
            next.setChips(temp);
            try {
                next.addChip(new Chip(playerColor), i);
//                options[i] = next.isGameOver() != null;
                if (next.isGameOver() != null) {
                    options.put(2, i);
                } else {
                    options.put(1, i);
                }
            } catch (ColumnFullException e) {
                //do nothing
//                System.out.println("column full in tryMoves");
//                System.out.println("note: options[" + i + "] = " + options[i]);
//                e.printStackTrace();
//                options[i] = null;
            }
        }
        return options;
    }

    // EFFECTS: selects the "best" move from given options
    //          if no moves are possible, throws EndGameException
    private int firstMove(HashMap<Integer, Integer> options) throws EndGameException {
//        ArrayList<Integer> moves = new ArrayList<>();
        if (options.size() > 0) {
            if (options.get(2) != -1) {
                return options.get(2);
            } else if (options.get(1) != -1) {
                return options.get(1);
            } else {
                throw new EndGameException();
            }
        }
        throw new EndGameException();
    }


    // MODIFIES: this
    //EFFECTS: if enter key is released, plays moves
    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
//                updateBoard(column);
                playMoves();
                updateBoard(column);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            } catch (ColumnFullException e) {
                JOptionPane.showMessageDialog(frame, "You can't add a chip here!",
                        "Column full", JOptionPane.ERROR_MESSAGE);
            } catch (EndGameException e) {
                JOptionPane.showMessageDialog(frame, "No more moves available. No points earned.",
                        "No moves left...", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        }
    }

    // note: must be overridden for KeyListener, but currently serves no purpose
    // EFFECTS: none
    @Override
    public void keyTyped(KeyEvent event) {

    }

    // MODIFIES: this
    // EFFECTS: updates board according to user input and checks if game is over.
    //          if game is over, closes window and allots points.
    public void updateBoard(int newCol) {
        if (board.isGameOver() != null) {
            if (playerColor.equals(board.isGameOver())) {
                JOptionPane.showMessageDialog(frame, "The player won! You earned 20 points.");
                saveable.addPoints(20);
            } else {
                JOptionPane.showMessageDialog(frame, "The player lost... You earned 1 point.");
                saveable.addPoints(1);
            }
            frame.dispose();
            try {
                saveable.write();
            } catch (IOException e) {
//                    e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "An error occurred while saving.",
                        "Uh oh", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (column != newCol) {
            components[0][newCol].setVisible(true);
            components[0][column].setVisible(false);
            column = newCol;
        }
    }

    // EFFECTS: clones a 2d Chip array
    private Chip[][] cloneChips(Chip[][] initial) {
        Chip[][] ret = new Chip[initial.length][initial[0].length];
        for (int r = 0; r < initial.length; r++) {
            System.arraycopy(initial[r], 0, ret[r], 0, initial[r].length);
        }
        return ret;
    }
//
//    public static void main(String[] args) {
//        ConnectWindow window;
//        try {
//            window = new ConnectWindow(new Saveable("./data/amy.json"));
//            window.displayFrame();
//
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//    }
}

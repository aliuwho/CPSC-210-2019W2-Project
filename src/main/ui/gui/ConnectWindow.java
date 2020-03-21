package ui.gui;

import model.Chip;
import model.FourBoard;
import model.exceptions.ColumnFullException;
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

public class ConnectWindow extends Window implements ActionListener, KeyListener {
    private Saveable saveable;
    private FourBoard board;
    private int column = 0;
    private JLabel label = createLabel("temp");
    private Image redChip;
    private Image blueChip;
    private Image emptyChip;
    private Image arrow;
    private int dimension;
    private JImageComponent[] arrows;
    private Color playerColor;

    public ConnectWindow(Saveable saveable) {
        super("Connect 4", 1, 1);
        if (getScreenHeight() > getScreenWidth()) {
            dimension = getScreenHeight() * 7 / 10;
        } else {
            dimension = getScreenWidth() * 7 / 10;
        }
        frame.setPreferredSize(new Dimension(dimension, dimension));
        this.saveable = saveable;
        this.board = new FourBoard();
        frame.setLayout(new GridLayout(7, 8));
        initImages();
        createArrows();
        chooseColor();
    }

    private void chooseColor() {
        Object[] colors = {"<Select a color>", "RED", "BLUE"};
        String colorName = (String) JOptionPane.showInputDialog(frame, createLabel("Select a color:"), "Select",
                JOptionPane.PLAIN_MESSAGE, null, colors, colors[0]);
        if (colorName != null) {
            if (!colorName.equals("<Select a color>")) {
                playerColor = Color.getColor(colorName);
            }
        } else {
            JLabel msg = createLabel("Please select a color!");
            JOptionPane.showMessageDialog(frame, msg, "Selection Error",
                    JOptionPane.ERROR_MESSAGE);
            chooseColor();
        }
    }


//    private Object[] getColors() {
//        Color[] colors = FourBoard.TYPES;
//        Object[] colorOptions = new Object[colors.length + 1];
//        System.arraycopy(colors, 0, colorOptions, 1, colors.length);
//        colorOptions[0] = "<Select a color>";
//        return colorOptions;
//    }

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
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates an arrow image at the top of each column
    private void createArrows() {
        arrows = new JImageComponent[7];
        for (int i = 0; i < arrows.length; i++) {
            JImageComponent arrow = new JImageComponent();
            arrow.setBufferedImage((BufferedImage) this.arrow);
            arrow.setVisible(false);
            arrows[i] = arrow;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "quit":
                frame.dispose();
                break;
            case "help":
                label.setText("help");
                updateBoard(moveRight());
                break;
            case "new":
                label.setText("new");
                updateBoard(moveLeft());
                break;
        }
        updateBoard(column);
    }

    private int moveLeft() {
        return Math.max(column - 1, 0);
    }

    private int moveRight() {
        return Math.min(column + 1, FourBoard.ROWS);
    }

    @Override
    protected void createFrame() {
        arrows[0].setVisible(true);
        for (int i = 0; i < 7; i++) {
            frame.add(arrows[i]);
        }
//        frame.add(new JPanel());
//        label = createLabel("temp");
        frame.add(label);
        for (int i = 0; i < 48; i++) {
            if (i == 7 | i == 15 | i == 23 /*| i == 31 *//*| i == 39*/ /*| i == 47*/) {
                frame.add(new JPanel());
            } else if (i == 31) {
                frame.add(createButton("How to Play", "help", this));
            } else if (i == 39) {
                frame.add(createButton("New Game", "new", this));
            } else if (i == 47) {
                frame.add(createButton("Quit", "quit", this));
            } else {
                JImageComponent hm = new JImageComponent();
                hm.setBufferedImage((BufferedImage) emptyChip);
                hm.setVisible(true);
                frame.add(hm);
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

    // EFFECTS:
    @Override
    public void keyTyped(KeyEvent e) {
//        displayInfo(e, "KEY TYPED: ");
        label.setText("Key: " + e.toString());
    }

    // EFFECTS:
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                label.setText("left");
                updateBoard(moveLeft());
                break;
            case KeyEvent.VK_RIGHT:
                updateBoard(moveRight());
                // move right
                break;
            case KeyEvent.VK_ENTER:
                label.setText("enter");
                try {
                    board.addChip(new Chip(playerColor), column);
                    // TODO: update board chips with correct color
                } catch (ColumnFullException ex) {
                    JOptionPane.showConfirmDialog(frame, "You can't put any more chips in this column!",
                            "Column FULL", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
        }
        updateBoard(column);
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        displayInfo(e, "KEY RELEASED: ");
    }

    public void updateBoard(int newCol) {
        if (isGameOver() != null) {
            JOptionPane.showMessageDialog(frame, "The game is over! " + isGameOver() + " won!");
        } else if (column != newCol) {
            arrows[newCol].setVisible(true);
            arrows[column].setVisible(false);
            column = newCol;
        }
    }

    public Color isGameOver() {
        if (board.isFourAcross() != null) {
            return board.isFourAcross();
        } else if (board.isFourUpDown() != null) {
            return board.isFourUpDown();

        } else {
            return board.isFourDiagonal();
        }

    }

    public static void main(String[] args) {
        ConnectWindow window = new ConnectWindow(null);
        window.displayFrame();
    }
}

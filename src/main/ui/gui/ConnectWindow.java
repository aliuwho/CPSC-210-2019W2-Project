package ui.gui;

import model.FourBoard;
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
    private JLabel label;
    private Image redChip;
    private Image blueChip;
    private Image emptyChip;
    private Image arrow;
    private int dimension;
    private JImageComponent[] arrows;

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
        try {
            int temp = dimension / 10;
            redChip = getScaledImage(ImageIO.read(new File("./data/red_circle.png")), temp, temp);
            blueChip = getScaledImage(ImageIO.read(new File("./data/blue_circle.png")), temp, temp);
            emptyChip = getScaledImage(ImageIO.read(new File("./data/grey_circle.png")), temp, temp);
            arrow = getScaledImage(ImageIO.read(new File("./data/arrow.png")), temp, temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createArrows();
    }

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
        if (e.getActionCommand().equals("quit")) {
            frame.dispose();
        } else {
            updateBoard(column);
        }
    }

    private int moveLeft() {
        if (column - 1 >= 0) {
            return column--;
        }
        return column;
    }

    private int moveRight() {
        if (column + 1 < FourBoard.ROWS) {
            label.setText("right");
            return column++;
        }
        return column;
    }

    @Override
    protected void createFrame() {
        arrows[0].setVisible(true);
        for (int i = 0; i < 7; i++) {
            frame.add(arrows[i]);
        }
//        frame.add(new JPanel());
        label = createLabel("temp");
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

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    private JPanel gamePanel() {
        JPanel panel = new JPanel();
//        panel.setBackground(Color.GRAY);
        return panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        displayInfo(e, "KEY TYPED: ");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
//            // Jump
//            case KeyEvent.VK_W:
            // climb e jump
//            case KeyEvent.VK_SPACE:
//                game.player.startClimb();
//                break;
            // move left
            case KeyEvent.VK_LEFT:
                label.setText("left");
                updateBoard(moveLeft());
                break;
            case KeyEvent.VK_RIGHT:
                updateBoard(moveRight());
                // move right
                break;
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

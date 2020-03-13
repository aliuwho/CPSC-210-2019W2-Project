package ui.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PetSelectWindow extends Window implements ActionListener {

    public PetSelectWindow() {
        super("Pet Selection", getScreenWidth() * 3 / 10, getScreenHeight() * 3 / 10);
        frame.setLayout(new GridLayout(2, 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "quit":
                frame.dispose();
            case "bird":
                //do osomething
            default:
                System.out.println("cool");
        }
    }

    @Override
    protected void createFrame() {
        frame.add(buttonPanel());
        frame.add(picturePanel());
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(createButton("Bird", "bird", this));
        buttonPanel.add(createButton("Cat", "cat", this));
        buttonPanel.add(createButton("Dog", "dog", this));
        buttonPanel.add(createButton("Horse", "horse", this));
        buttonPanel.add(createButton("Lizard", "lizard", this));
        buttonPanel.add(createButton("Cancel", "quit", this));
        return buttonPanel;
    }

    private JPanel picturePanel() {
        JPanel picPanel = new JPanel();
        try {
            File f = new File("./src/tobs.jpg");
            if (f.setReadable(true)) {
                JImageComponent ic = new JImageComponent();
                ic.loadImage(f);
//                System.out.println("all good");
                BufferedImage myPicture = ImageIO.read(f);
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                picPanel.add(picLabel);
                picPanel.add(createLabel(new ImageIcon(myPicture)));
                picPanel.add(createLabel(new ImageIcon(myPicture)));
                picPanel.add(createLabel(new ImageIcon(myPicture)));
                picPanel.add(createLabel(new ImageIcon(myPicture)));
                picPanel.add(createLabel(new ImageIcon(myPicture)));
            }
            JImageComponent ic = new JImageComponent();
            ic.loadImage(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return picPanel;
    }

    private JLabel createLabel(ImageIcon imageIcon) {
        return new JLabel(imageIcon);
    }
}

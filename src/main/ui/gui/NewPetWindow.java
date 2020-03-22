package ui.gui;

import model.pets.*;
import persistence.Saveable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewPetWindow extends Window implements ActionListener {
    private Saveable saveable;

    public NewPetWindow(Saveable saveable) {
        super("Pet Selection", getScreenWidth() * 3 / 10, getScreenHeight() * 3 / 10);
        frame.setLayout(new GridLayout(2, 1));
        this.saveable = saveable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (!cmd.equals("cancel")) {
            String petName = JOptionPane.showInputDialog("Name your pet:");
            if (petName != null && petName.length() > 0) {
                Pet pet = getPet(cmd, petName);
                saveable.getPets().add(pet);
//                saveable.write();
                frame.dispose();
                PetRoomWindow room = new PetRoomWindow(saveable, pet);
                room.displayFrame();
            } else {
                JOptionPane.showMessageDialog(frame, "That's not a valid name!", "error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            frame.dispose();
        }
    }

    private Pet getPet(String cmd, String name) {
        Pet pet;
        switch (cmd) {
            case "bird":
                pet = new Bird(name);
                break;
            case "cat":
                pet = new Cat(name);
                break;
            case "dog":
                pet = new Dog(name);
                break;
            case "horse":
                pet = new Horse(name);
                break;
            case "lizard":
                pet = new Lizard(name);
                break;
            default:
//                System.out.println("Selection not valid... Try again!");
                pet = null;
                break;
        }
        return pet;
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
        buttonPanel.add(createButton("Cancel", "cancel", this));
        return buttonPanel;
    }

    private JPanel picturePanel() {
        JPanel picPanel = new JPanel();
        try {
            File f = new File("./data/tobs2.jpg");
            if (f.setReadable(true)) {
                JImageComponent ic = new JImageComponent();
                ic.loadImage(f);
//                System.out.println("all good");
                BufferedImage myPicture = ImageIO.read(f);
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                picPanel.add(picLabel);
//                picPanel.add(createLabel(new ImageIcon(myPicture)));
//                picPanel.add(createLabel(new ImageIcon(myPicture)));
//                picPanel.add(createLabel(new ImageIcon(myPicture)));
//                picPanel.add(createLabel(new ImageIcon(myPicture)));
//                picPanel.add(createLabel(new ImageIcon(myPicture)));
            }
            JImageComponent ic = new JImageComponent();
            ic.loadImage(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return picPanel;
    }

//    private JLabel createLabel(ImageIcon imageIcon) {
//        return new JLabel(imageIcon);
//    }
}

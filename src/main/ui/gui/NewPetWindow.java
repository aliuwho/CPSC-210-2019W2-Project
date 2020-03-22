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

/**
 * A class representing GUI to get a new pet
 */
public class NewPetWindow extends Window implements ActionListener {
    private Saveable saveable;

    // EFFECTS: creates new pet window
    public NewPetWindow(Saveable saveable) {
        super("Pet Selection", getScreenWidth() * 3 / 10, getScreenHeight() * 3 / 10);
        frame.setLayout(new GridLayout(2, 1));
        this.saveable = saveable;
    }

    // MODIFIES: this
    // EFFECTS: process action event command
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

    // EFFECTS: processes user selection
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

    // MODIFIES: this
    // EFFECTS: creates window frame
    @Override
    protected void createFrame() {
        frame.add(buttonPanel());
        frame.add(picturePanel());
    }

    // EFFECTS: creates a JPanel with buttons for each type of pet
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

    // EFFECTS: creates a JPanel with an image
    private JPanel picturePanel() {
        JPanel picPanel = new JPanel();
        String[] animals = {"bird", "cat", "dog", "horse", "lizard"};
        for (String animal : animals) {
            try {
                JImageComponent temp = new JImageComponent();
                Image img = getScaledImage(ImageIO.read(new File("./data/" + animal + ".jpg")),
                        getScreenWidth() / 15, getScreenHeight() / 15);
                temp.setBufferedImage((BufferedImage) img);
                picPanel.add(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        try {
//            JImageComponent bird = new JImageComponent();
//            Image birdImage = getScaledImage(ImageIO.read(new File("./data/bird.jpg")),
//                    getScreenWidth() / 10, getScreenHeight() / 10);
//            bird.setBufferedImage((BufferedImage) birdImage);
//            JImageComponent cat = new JImageComponent();
//            Image catImage = getScaledImage(ImageIO.read(new File("./data/cat.jpg")),
//                    getScreenWidth() / 10, getScreenHeight() / 10);
//            cat.setBufferedImage((BufferedImage) catImage);
//            JImageComponent dog = new JImageComponent();
//            dog.loadImage(new File("./data/dog.jpg"));
//            JImageComponent horse = new JImageComponent();
//            horse.loadImage(new File("./data/horse.jpg"));
//            JImageComponent lizard = new JImageComponent();
//            lizard.loadImage(new File("./data/lizard.jpg"));
//            frame.add(bird);
//            frame.add(cat);
//            frame.add(dog);
//            frame.add(horse);
//            frame.add(lizard);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return picPanel;
    }

//    private JLabel createLabel(ImageIcon imageIcon) {
//        return new JLabel(imageIcon);
//    }
}

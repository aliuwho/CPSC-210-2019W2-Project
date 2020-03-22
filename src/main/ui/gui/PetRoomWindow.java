package ui.gui;

import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;
import model.pets.Pet;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * a class representing a window to interact with pets
 */
public class PetRoomWindow extends Window implements ActionListener {
    private Saveable saveable;
    private Pet pet;
    private JLabel hungry;
    private JLabel tired;

    // EFFECTS: creates a new pet room window
    public PetRoomWindow(Saveable saveable, Pet pet) {
        super("Pet Room", getScreenWidth() * 5 / 10, getScreenHeight() * 5 / 10);
        this.saveable = saveable;
//        this.pet = saveable.findPet(pet.getName());
        frame.setLayout(new GridLayout());
        this.pet = pet;
    }

    // MODIFIES: this
    // EFFECTS: processes action event
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("play".equals(cmd)) {
            play();
        } else if ("nap".equals(cmd)) {
            nap();
        } else if ("feed".equals(cmd)) {
            feed();
        } else {
            frame.dispose();
            try {
                saveable.write();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        hungry.setText("hungry = " + pet.isHungry());
        tired.setText("tired = " + pet.isTired());
    }

    // MODIFIES: this
    // EFFECTS: creates window frame
    @Override
    protected void createFrame() {
        frame.add(statusPanel());
        frame.add(buttonPanel());
    }

    // EFFECTS: creates a status panel
    private JPanel statusPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(createLabel(pet.getName() + ", " + pet.getSpecies()));
        panel.add(createLabel("this is filler text :("));
        hungry = createLabel("hunger: = " + pet.isHungry());
        panel.add(hungry);
        tired = createLabel("tired = " + pet.isTired());
        panel.add(tired);
        return panel;
    }

    // EFFECTS: creates buttons to interact with pet
    private JPanel buttonPanel() {
        JPanel panel = new JPanel();
        panel.add(createButton("Feed", "feed", this));
        panel.add(createButton("Play", "play", this));
        panel.add(createButton("Nap", "nap", this));
        panel.add(createButton("Quit", "quit", this));
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: play with pet
    private void play() {
        try {
            pet.play();
            saveable.addPoints(10);
            JOptionPane.showMessageDialog(frame, pet.getName() + " enjoyed playing with you!");
        } catch (TiredException ex) {
//                    ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, pet.getName() + " is too tired to play. Try taking a nap!",
                    "Pet is tired!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIE: this
    // EFFECTS: feed pet
    private void feed() {
        try {
            pet.feed();
            saveable.addPoints(10);
            JOptionPane.showMessageDialog(frame, pet.getName() + " enjoyed the meal. Yummy!");
        } catch (NotHungryException ex) {
//            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, pet.getName() + " isn't hungry!",
                    "Pet is full", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: nap with pet
    private void nap() {
        try {
            pet.sleep();
            saveable.addPoints(10);
            JOptionPane.showMessageDialog(frame, pet.getName() + " took a lovely nap and feels well-rested.");
        } catch (NotTiredException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, pet.getName() + " isn't tired!",
                    "Pet is not tired", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

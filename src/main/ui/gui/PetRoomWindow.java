package ui.gui;

import model.Pet;
import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a class representing a window to interact with pets
 */
public class PetRoomWindow extends Window implements ActionListener {
    private final Pet pet;
    private JLabel hungry;
    private JLabel tired;
    private int interactions;

    // EFFECTS: creates a new pet room window
    public PetRoomWindow(Pet pet) {
        super("Pet Room", getScreenWidth() * 5 / 10, getScreenHeight() * 5 / 10);
//        this.pet = saveable.findPet(pet.getName());
        frame.setLayout(new GridLayout(1, 2));
        this.pet = pet;
        interactions = 0;
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
        }
        updateStatus();
    }

    // MODIFIES: this
    // EFFECTS: updates hunger and tired status of pet
    private void updateStatus() {
        if (pet.isHungry()) {
            hungry.setText("Your pet is hungry! Please feed " + pet.getName() + "...");
        } else {
            hungry.setText("Your pet is full :)");
        }
        if (pet.isTired()) {
            tired.setText("Your pet is tired. Take a nap!");
        } else {
            tired.setText(pet.getName() + " is full of energy!");
        }
//        hungry.setText("hungry = " + pet.isHungry());
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
        panel.setLayout(new GridLayout(3, 1));
        panel.add(createLabel("Current Pet: " + pet.getName() + " (" + pet.getSpecies() + ")"));
//        panel.add(createLabel(pet.getName() + " (" + pet.getSpecies() + ")"));
        hungry = createLabel("hunger");
        panel.add(hungry);
        tired = createLabel("tired");
        panel.add(tired);
        updateStatus();
        return panel;
    }

    // EFFECTS: creates buttons to interact with pet
    private JPanel buttonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
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
            interactions++;
            JOptionPane.showMessageDialog(frame, pet.getName() + " enjoyed playing with you!");
        } catch (TiredException ex) {
//                    ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, pet.getName() + " is too tired to play. Try taking a nap!",
                    "Pet is tired!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: feed pet
    private void feed() {
        try {
            pet.feed();
            interactions++;
            JOptionPane.showMessageDialog(frame, pet.getName() + " enjoyed the meal. Yummy!");
        } catch (NotHungryException ex) {
            JOptionPane.showMessageDialog(frame, pet.getName() + " isn't hungry!",
                    "Pet is full", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: nap with pet
    private void nap() {
        try {
            pet.sleep();
            interactions++;
            JOptionPane.showMessageDialog(frame, pet.getName() + " took a lovely nap and feels well-rested.");
        } catch (NotTiredException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, pet.getName() + " isn't tired!",
                    "Pet is not tired", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // EFFECTS: returns interactions
    public int getInteractions() {
        return interactions;
    }
}

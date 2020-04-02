package ui.gui;

import model.Pet;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class representing main menu GUI elements for the application
 */
public class MainMenuWindow extends Window implements ActionListener {
    private final Saveable saveable;
    private JProgressBar levels;

    // EFFECTS: creates a new MainMenuWindow
    public MainMenuWindow(Saveable s) {
        super("Main Menu", getScreenWidth() * 3 / 10, (int) (getScreenHeight() * 0.7));
        saveable = s;
//        frame.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: adds elements to window
    @Override
    public void createFrame() {
        frame.setLayout(new GridLayout(3, 1));
        frame.add(userInfoPanel()); //puts panel on top
        frame.add(createLabel(new ImageIcon("./data/tobs.jpg")));
        frame.add(buttonPanel());
    }

    // EFFECTS: creates a user info JPanel
    public JPanel userInfoPanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(4, 1));
        welcomePanel.add(createLabel("Welcome " + saveable.getUsername() + "!"));
        welcomePanel.add(createLabel("User since " + saveable.getStart()));

        welcomePanel.add(createLabel("Level Progress:"));
        levels = new JProgressBar(0, 500);
        levels.setValue(Math.toIntExact(saveable.getPoints()));
        levels.setStringPainted(true);
        welcomePanel.add(levels);
        return welcomePanel;
    }

    // EFFECTS: creates a JPanel with buttons
    public JPanel buttonPanel() {
        JPanel buttonsPanel = new JPanel();
        //rows equal to num of buttons:
        buttonsPanel.setLayout(new GridLayout(4, 1));
//        buttonsPanel.setLayout(new GridBagLayout());
//        GridBagConstraints buttonConstraints = new GridBagConstraints();
//        buttonConstraints.insets = new Insets(3, 3, 0, 0);
//        buttonConstraints.fill = GridBagConstraints.HORIZONTAL; //makes buttons same width
//        buttonConstraints.anchor = GridBagConstraints.LINE_START;
//        buttonConstraints.ipady = 20;
//        buttonConstraints.gridy = 0;
        buttonsPanel.add(createButton("Writing Desk", "writingDesk",
                this));
//        buttonConstraints.gridy = 1;
        buttonsPanel.add(createButton("Pet Room", "petMenu", this));
//        buttonConstraints.gridy = 2;
        buttonsPanel.add(createButton("Connect 4", "4menu", this));
//        buttonConstraints.gridy = 3;
//        buttonsPanel.add(createButton("Build Blocks", "buildMenu", this));
//        buttonConstraints.gridy = 4;
        buttonsPanel.add(createButton("Quit", "quit", this));
        return buttonsPanel;
    }

    // MODIFIES: this
    // EFFECTS: processes action command
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "quit":
                updateInfo();
                frame.dispose();
                break;
            case "writingDesk":
                WritingDeskWindow writeDesk = new WritingDeskWindow(saveable.getLibrary());
                writeDesk.displayFrame();
//                updateInfo();
                break;
            case "petMenu":
                selectPet();
                break;
            case "4menu":
                chooseColor();
//                ConnectWindow connect = new ConnectWindow(saveable);
//                connect.displayFrame();
                break;
        }
        updateInfo();
    }

    // MODIFIES: this
    // EFFECTS: updates information for user
    public void updateInfo() {
        levels.setValue(Math.toIntExact(saveable.getPoints())); //update levels progress
//        levels.;
        try {
            saveable.write();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Uh oh..."
                            + " your data couldn't be saved. Press 'ok' to close the application.",
                    "CRASH and BURN", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: allows player to select blue or red as their color
    private void chooseColor() {
        Object[] colors = {"<Select a color>", "RED", "BLUE"};
        String colorName = (String) JOptionPane.showInputDialog(frame, createLabel("Select a color:"), "Select",
                JOptionPane.QUESTION_MESSAGE, null, colors, colors[0]);
        if (colorName != null) {
            if (colorName.equals("<Select a color>")) {
                JLabel msg = createLabel("Please select a color!");
                JOptionPane.showMessageDialog(frame, msg, "Selection Error",
                        JOptionPane.ERROR_MESSAGE);
                chooseColor();
            } else {
                Color player;
                if (colorName.equals("RED")) {
                    player = Color.RED;
                } else {
                    player = Color.BLUE;
                }
                playConnect(player);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: plays connect4 game
    public void playConnect(Color player) {
        ConnectWindow connect = new ConnectWindow(player);
        connect.displayFrame();
//        System.out.println("not ongoing");
//        if (connect.getWinner() != null) {
//            if (connect.getWinner().equals(player)) {
//                saveable.addPoints(20);
//            } else {
//                saveable.addPoints(1);
//            }
//        }
        saveable.addPoints(10);

    }

    // MODIFIES: this
    // EFFECTS: if player won, award 20 points; else, award 1 point
//    private void checkWinner(ConnectWindow connect, Color player) {
//        if (connect.getWinner() != null) {
//            if (connect.getWinner().equals(player)) {
////                JOptionPane.showMessageDialog(frame, "The player won! You earned 20 points.");
//                saveable.addPoints(20);
//            } else {
////                JOptionPane.showMessageDialog(frame, "The player lost... You earned 1 point.");
//                saveable.addPoints(1);
//            }
//        }
//    }

    // EFFECTS: returns a generic array of pet names
    private Object[] getPetNames() {
        ArrayList<Pet> pets = saveable.getPets();
        Object[] petNames = new Object[pets.size() + 2];
        for (int i = 0; i < pets.size(); i++) {
            petNames[i + 1] = pets.get(i).getName();
        }
        petNames[0] = "<Select a pet>";
        petNames[pets.size() + 1] = "<Get another pet>";
        return petNames;
    }

    // EFFECTS: enables user to select a pet to interact with
    private void selectPet() {
        Object[] petNames = getPetNames();
        String petName = (String) JOptionPane.showInputDialog(frame, createLabel("Select a pet:"), "Select",
                JOptionPane.PLAIN_MESSAGE, null, petNames, petNames[0]);
        if (petName != null) {
            if (!petName.equals("<Select a pet>")) {
                if (petName.equals("<Get another pet>")) {
                    NewPetWindow newPet = new NewPetWindow(saveable);
                    newPet.displayFrame();
                } else {
                    PetRoomWindow room = new PetRoomWindow(saveable.findPet(petName));
                    room.displayFrame();
                    saveable.addPoints(room.getInteractions() * 20);
                }
            } else {
                JLabel msg = createLabel("Please select a pet!");
                JOptionPane.showMessageDialog(frame, msg, "Selection Error",
                        JOptionPane.ERROR_MESSAGE);
                selectPet();
            }
        }
    }

}

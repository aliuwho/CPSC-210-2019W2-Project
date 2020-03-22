package ui.gui;

import model.pets.Pet;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenuWindow extends Window implements ActionListener {
    private Saveable saveable;

    public MainMenuWindow(Saveable s) {
        super("Main Menu", getScreenWidth() * 3 / 10, (int) (getScreenHeight() * 0.7));
        saveable = s;
//        frame.setLayout(new BorderLayout());
    }

    //TODO: ADD METHOD SPECIFICATION

    @Override
    public void createFrame() {
        frame.setLayout(new GridLayout(3, 1));
        frame.add(userInfoPanel()); //puts panel on top
        frame.add(createLabel(new ImageIcon("./data/tobs.jpg")));
        frame.add(buttonPanel());
    }

    public JPanel userInfoPanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(4, 1));
        welcomePanel.add(createLabel("Welcome " + saveable.getUsername() + "!"));
        welcomePanel.add(createLabel("User since " + saveable.getStart()));

        welcomePanel.add(createLabel("Level Progress:"));
        JProgressBar levels = new JProgressBar(0, 1000);
        levels.setValue(Math.toIntExact(saveable.getPoints()));
        levels.setStringPainted(true);
        welcomePanel.add(levels);
        return welcomePanel;
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            try {
                saveable.write();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.dispose();
        } else if (e.getActionCommand().equals("writingDesk")) {
            WritingDeskWindow writeDesk = new WritingDeskWindow(saveable);
            writeDesk.displayFrame();
        } else if (e.getActionCommand().equals("petMenu")) {
            selectPet();
        } else if (e.getActionCommand().equals("4menu")) {
            ConnectWindow connect = new ConnectWindow(saveable);
            connect.displayFrame();
        }
    }

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
                    PetRoomWindow room = new PetRoomWindow(saveable, saveable.findPet(petName));
                    room.displayFrame();
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

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
        super("Main Menu", (int) (getScreenWidth() * 0.7), (int) (getScreenHeight() * 0.7));
        saveable = s;
//        frame.setLayout(new BorderLayout());
    }

    //TODO: ADD METHOD SPECIFICATION

    @Override
    public void createFrame() {
//        frame.add(new JPanel());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        frame.add(userInfoPanel(), constraints); //puts panel on top
//        frame.add(new JPanel());
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(buttonPanel(), constraints); //puts panel on left
//        frame.add(new JPanel());
//        frame.add(new JPanel());
        //add buttons panel to content pane which by default uses border layout
//        frame.getContentPane().add(levelPanel(),BorderLayout.NORTH);

    }

    /*public JPanel userInfoPanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 0, 0);
//        constraints.gridx = 1;
        constraints.gridy = 0;
        welcomePanel.add(createLabel("Welcome " + saveable.getUsername() + "!"), constraints);
        constraints.gridy = 1;
        welcomePanel.add(new JLabel(), constraints); //empty space
        constraints.gridy = 2;
        welcomePanel.add(createLabel(saveable.getStart()), constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        welcomePanel.add(createLabel("Level Progress:"), constraints);
        constraints.gridx = 1;
        JProgressBar levels = new JProgressBar(0, 1000);
        levels.setValue(Math.toIntExact(saveable.getPoints()));
        levels.setStringPainted(true);
        welcomePanel.add(levels, constraints);
        return welcomePanel;
    }*/
    public JPanel userInfoPanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.add(createLabel("Welcome " + saveable.getUsername() + "!"));
        welcomePanel.add(createLabel("User since " + saveable.getStart()));

        welcomePanel.add(createLabel("Level Progress:"));
        JProgressBar levels = new JProgressBar(0, 1000);
        levels.setValue(Math.toIntExact(saveable.getPoints()));
        levels.setStringPainted(true);
        levels.setPreferredSize(new Dimension(getScreenWidth() * 2 / 10, getScreenHeight() * 2 / 10));
        welcomePanel.add(levels);
        return welcomePanel;
    }

    public JPanel buttonPanel() {
        JPanel buttonsPanel = new JPanel();
        //rows equal to num of buttons:
//        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.insets = new Insets(3, 3, 0, 0);
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL; //makes buttons same width
        buttonConstraints.anchor = GridBagConstraints.LINE_START;
        buttonConstraints.ipady = 20;
        buttonConstraints.gridy = 0;
//        JButton writingButton = new JButton("Writing Desk");
//        writingButton.setActionCommand("writingDesk");
//        writingButton.addActionListener(this);
//        writingButton.setFont(font);
        buttonsPanel.add(createButton("Writing Desk", "writingDesk",
                this), buttonConstraints);
        buttonConstraints.gridy = 1;
//        JButton petButton = new JButton("Pet Room");
//        petButton.setFont(font);
        buttonsPanel.add(createButton("Pet Room", "petMenu", this), buttonConstraints);
        buttonConstraints.gridy = 2;
//        JButton buildButton = new JButton("Build Blocks");
//        buildButton.setFont(font);
        buttonsPanel.add(createButton("Build Blocks", "buildMenu", this), buttonConstraints);
        buttonConstraints.gridy = 3;
//        JButton quitButton = new JButton("Quit");
//        quitButton.setFont(font);
        buttonConstraints.gridy = 4;
        buttonsPanel.add(createButton("Quit", "quit", this), buttonConstraints);
//        quitButton.setActionCommand("quit");
//        quitButton.addActionListener(this);
        return buttonsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            frame.dispose();
        } else if (e.getActionCommand().equals("writingDesk")) {
//            System.out.println("this is a filler lol");
            WritingDeskWindow writeDesk = new WritingDeskWindow(saveable);
            writeDesk.displayFrame();
        } else if (e.getActionCommand().equals("petMenu")) {
            /*if (saveable.getPets().size() == 0) {
                NewPetWindow select = new NewPetWindow(saveable);
                select.displayFrame();
            } else {
                selectPet();
            }*/
            selectPet();
        }
        try {
            saveable.write();
        } catch (IOException ex) {
            ex.printStackTrace();
            frame.dispose();
        }
    }

    //TODO: alphabetize stories and pets
    //TODO: add timedate stamps to pets
    //TODO: if no timedate given, default to jan 1, 2020
    private Object[] getPetNames() {
        ArrayList<Pet> pets = saveable.getPets();
        Object[] petNames = new Object[pets.size() + 2];
        for (int i = 0; i < pets.size(); i++) {
//            System.out.println("\t" + (i + 1) + " -> Select " + pets.get(i).getName());
            petNames[i + 1] = pets.get(i).getName();
        }
        petNames[0] = "<Select a pet>";
        petNames[pets.size() + 1] = "<Get another pet>";
        return petNames;
    }

    private void selectPet() {
        /*int select = JOptionPane.showConfirmDialog(frame, "Would you like to get another pet?",
                "In with the new?", JOptionPane.YES_NO_OPTION);
        if (select == 0) {

        } else {*/
        Object[] petNames = getPetNames();
//                Object[] possib = {"test", "oops", "help"};
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

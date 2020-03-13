package ui.gui;

import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuWindow extends Window implements ActionListener {
    private Saveable saveable;

    public MainMenuWindow(Saveable s) {
        super("Main Menu", (int) (getScreenWidth() * 0.7), (int) (getScreenHeight() * 0.7));
        saveable = s;
        frame.setLayout(new BorderLayout());
    }

    //TODO: ADD METHOD SPECIFICATION

    @Override
    public void createFrame() {
//        frame.setPreferredSize(new Dimension((int) (getScreenWidth() * 0.7), (int) (screenHeight * 0.7)));
        //add buttons panel to content pane which by default uses border layout
        frame.getContentPane().add(userInfoPanel(), BorderLayout.NORTH); //puts panel on top
        frame.getContentPane().add(buttonPanel(), BorderLayout.WEST); //puts panel on left

    }

    public JPanel userInfoPanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());
        GridBagConstraints welcomeConstraints = new GridBagConstraints();
        welcomeConstraints.insets = new Insets(2, 2, 0, 0);
        welcomeConstraints.gridx = 0;
        welcomeConstraints.gridy = 0;
        JLabel greeting = new JLabel("Welcome " + saveable.getUsername() + "!");
        greeting.setFont(font);
        welcomePanel.add(greeting, welcomeConstraints);
        welcomeConstraints.gridy = 1;
        welcomePanel.add(new JLabel(), welcomeConstraints); //empty space
        welcomeConstraints.gridy = 2;
        JLabel levelLabel = new JLabel("Level progress: ");
        levelLabel.setFont(font);
        welcomePanel.add(levelLabel, welcomeConstraints);
        welcomeConstraints.gridx = 1;
        JProgressBar levels = new JProgressBar();
        welcomePanel.add(levels, welcomeConstraints);
        return welcomePanel;
    }

    public JPanel buttonPanel() {
        JPanel buttonsPanel = new JPanel();
        //rows equal to num of buttons:
//        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.insets = new Insets(2, 2, 0, 0);
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
            try {
                saveable.write();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("writingDesk")) {
            // MAKE IT SO WE CREATE A DESK WINDOW TO INTERACT WITH
            System.out.println("this is a filler lol");
            WritingDeskWindow writeDesk = new WritingDeskWindow(saveable);
            writeDesk.displayFrame();
        }
    }
}

package ui.gui;

import persistence.Saveable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends Window implements ActionListener {
    private Saveable saveable;

    public MainMenuWindow(Saveable s) {
        super("Main Menu", (int) (getScreenWidth() * 0.7), (int) (getScreenHeight() * 0.7));
        saveable = s;
    }

    //TODO: ADD METHOD SPECIFICATION

    @Override
    public void createFrame() {
//        frame.setPreferredSize(new Dimension((int) (getScreenWidth() * 0.7), (int) (screenHeight * 0.7)));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));

        //left panel for buttons
        /*try {
            File f = new File("./src/tobs.jpg");
            if (f.setReadable(true)) {
                JImageComponent ic = new JImageComponent();
                ic.loadImage(f);
                BufferedImage myPicture = ImageIO.read(f);
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                frame.add(picLabel);
            }
            JImageComponent ic = new JImageComponent();
            ic.loadImage(f);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //add buttons panel to content pane which by default uses border layout
        frame.getContentPane().add(userInfoPanel(), BorderLayout.NORTH); //puts panel on top
        frame.getContentPane().add(buttonsPanel(), BorderLayout.WEST); //puts panel on left
        displayFrame();

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

    public JPanel buttonsPanel() {
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
        buttonsPanel.add(createButton("Writing Desk", "writingMenu",
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
        } else if (e.getActionCommand().equals("writingMenu")) {
            // MAKE IT SO WE CREATE A DESK WINDOW TO INTERACT WITH
            System.out.println("this is a filler lol");
        }
    }
}

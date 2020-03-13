package ui.gui;

import model.exceptions.EmptyLibraryException;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WritingDeskWindow extends Window implements ActionListener {

    private Saveable saveable;
    private String storyName;
    private JTextField enterName;
    private JPanel panel;

    public WritingDeskWindow(Saveable saveable) {
        super("Writing Desk", getScreenWidth() * 4 / 10, getScreenHeight() * 2 / 10);
        this.saveable = saveable;
        frame.setLayout(new BorderLayout());
    }

    @Override
    public void createFrame() {
        frame.add(buttonPanel(), BorderLayout.WEST);
        frame.add(viewPanel(), BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("createStory")) {
            CreateStoryWindow csw = new CreateStoryWindow();
            csw.displayFrame();
        } else if (e.getActionCommand().equals("enterStoryName")) {
            storyName = enterName.getText();
        } else if (e.getActionCommand().equals("quit")) {
            frame.dispose();
        }

    }

    /*// EFFECTS: runs Story Add option
    public void addStory(Library l, Story story) {
        StoryAddMenu storyAddMenu = new StoryAddMenu(input, username, story, l);
        storyAddMenu.runApp();
    }*/

    public JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridx = 0;
        buttonPanel.add(createButton("Create a Story", "createStory", this), constraints);
        constraints.gridy = 1;
        buttonPanel.add(createButton("Return to Main Menu", "quit", this), constraints);
//        constraints.gridx = 2;
        return buttonPanel;
    }

    public JPanel viewPanel() {
        JPanel viewPanel = new JPanel();
        JList storyList;
        try {
            String[] storyNames = saveable.getLibrary().getStoryNames();
            storyList = new JList(storyNames);
        } catch (EmptyLibraryException e) {
//            e.printStackTrace();
            String[] storyNames = {"<No Stories to View>"};
            storyList = new JList(storyNames);
        }
        storyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane storyViewer = new JScrollPane(storyList);
        storyViewer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        storyViewer.setPreferredSize(new Dimension(200, 120));
        viewPanel.add(storyViewer);
        return viewPanel;
    }
}

package ui.gui;

import model.Story;
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
        super("Writing Desk", (int) (getScreenWidth() * 0.5), (int) (getScreenHeight() * 0.5));
        this.saveable = saveable;
    }

    @Override
    public void createFrame() {
//        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridx=;
        frame.add(createButton("Create a Story", "createStory", this), constraints);
        constraints.gridx = 3;
        JList stories;
        try {
            String[] storyNames = saveable.getLibrary().getStoryNames();
            stories = new JList(storyNames);
        } catch (EmptyLibraryException e) {
            e.printStackTrace();
            String[] storyNames = {"Your library is currently empty!"};
            stories = new JList(storyNames);
        }
        stories.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        stories.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        stories.setVisibleRowCount(-1);

        DefaultListModel<Story> huh = new DefaultListModel<>();
        JScrollPane listScroller = new JScrollPane(stories);
        listScroller.setPreferredSize(new Dimension(250, 80));
        frame.add(stories, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        frame.add(createButton("Return to Main Menu", "quit", this), constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
//        frame.add(panel, constraints);
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
}

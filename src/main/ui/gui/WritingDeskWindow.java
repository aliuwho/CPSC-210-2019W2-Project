package ui.gui;

import model.Library;
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
        switch (e.getActionCommand()) {
            case "createStory":
//            CreateStoryWindow csw = new CreateStoryWindow();
//            csw.displayFrame();
                createStory();
                break;
            case "enterStoryName":
                storyName = enterName.getText();
                break;
            case "quit":
                frame.dispose();
                break;
            case "view":
                selectStory();
                break;
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
        constraints.insets = new Insets(2, 2, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL; //makes buttons same width
//        constraints.gridx = 0;
        buttonPanel.add(createButton("Create a Story", "createStory", this), constraints);
        constraints.gridy = 1;
        buttonPanel.add(createButton("View a Story", "view", this), constraints);
        constraints.gridy = 2;
        buttonPanel.add(createButton("Return to Main Menu", "quit", this), constraints);
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

    public void selectStory() {
//        Object[] storyNames;
        try {
            Object[] storyNames = storyOptions();
            String storyName = (String) JOptionPane.showInputDialog(frame, createLabel("Select a story:"), "Select",
                    JOptionPane.PLAIN_MESSAGE, null, storyNames, storyNames[0]);

            if (storyName != null) {
                if (!storyName.equals("<Select a story>")) {
                    ViewStoryWindow view = new ViewStoryWindow();
                    view.displayFrame();
                } else {
                    JLabel msg = createLabel("Please select a story!");
                    JOptionPane.showMessageDialog(frame, msg, "Selection Error",
                            JOptionPane.ERROR_MESSAGE);
                    selectStory();
                }

            }

        } catch (HeadlessException e) {
            e.printStackTrace();
            frame.dispose();
        }
    }

    private Object[] storyOptions() {
        Library library = saveable.getLibrary();
        Object[] storyNames = new Object[library.getSize() + 1];
        storyNames[0] = "<Select a story>";
        for (int i = 0; i < library.getSize(); i++) {
            storyNames[i + 1] = library.getStories().get(i);
        }
        return storyNames;
    }

    private void createStory() {
        String storyName = JOptionPane.showInputDialog("Name your story:");
        if (storyName != null && storyName.length() > 0) {
            storyName = removeSpaces(storyName);
            WriteStoryWindow wsw = new WriteStoryWindow(storyName);
            wsw.displayFrame();
//            frame.dispose();
        }
    }

    // EFFECTS: removes spaces from a given string
    private String removeSpaces(String input) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String character = input.substring(i, i + 1);
            if (character.equals(" ")) {
                ret.append("_");
            } else {
                ret.append(character);
            }
        }
        return ret.toString();
    }
}

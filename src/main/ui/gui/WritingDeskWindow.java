package ui.gui;

import model.Library;
import model.Story;
import model.WritingPrompt;
import persistence.Saveable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * a class representing a GUI window for the writing desk functions
 */
public class WritingDeskWindow extends Window implements ActionListener {

    private final Saveable saveable;
    //    private JPanel panel;
    private final Library library;
    private JList<Object> storyList;
    private DefaultListModel<Object> storyListModel;

    // EFFECTS: creates a new WritingDeskWindow
    public WritingDeskWindow(Saveable saveable) {
        super("Writing Desk", getScreenWidth() * 4 / 10, getScreenHeight() * 3 / 10);
        this.saveable = saveable;
        this.library = saveable.getLibrary();
        frame.setLayout(new BorderLayout());
    }

    // EFFECTS: creates window frame
    @Override
    public void createFrame() {
        frame.add(buttonPanel(), BorderLayout.WEST);
        frame.add(viewPanel(), BorderLayout.EAST);
        frame.add(imagePanel(), BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: processes action event
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("create".equals(cmd)) {
            String storyName = JOptionPane.showInputDialog(createLabel("What would you like to call your story?"));
            createStory(storyName);
        } else if ("quit".equals(cmd)) {
            try {
                saveable.write();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.dispose();
        } else if ("view".equals(cmd)) {
            selectStory();
        } else if ("delete".equals(cmd)) {
            deleteStory();
        } else if ("prompt".equals(cmd)) {
            JOptionPane.showMessageDialog(frame, new WritingPrompt(),
                    "Here's an idea?", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // EFFECTS: creates a panel with an image
    public JPanel imagePanel() {
        JPanel imagePanel = new JPanel();
        try {
            JImageComponent books = new JImageComponent();
            Image bookImage = getScaledImage(ImageIO.read(new File("./data/books.jpeg")),
                    getScreenWidth() / 10, getScreenHeight() / 10);
            books.setBufferedImage((BufferedImage) bookImage);
            imagePanel.add(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        imagePanel.add(createLabel(new ImageIcon("./data/books.jpeg")));
        return imagePanel;
    }

    // EFFECTS: creates a JPanel with buttons for writing desk options
    public JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL; //makes buttons same width
//        constraints.gridx = 0;
        buttonPanel.add(createButton("Create a Story", "create", this), constraints);
        constraints.gridy = 1;
        buttonPanel.add(createButton("View Selected Story", "view", this), constraints);
        constraints.gridy = 2;
        buttonPanel.add(createButton("Delete Selected Story", "delete", this), constraints);
        constraints.gridy = 3;
        buttonPanel.add(createButton("Generate a Writing Prompt", "prompt", this), constraints);
        constraints.gridy = 4;
        buttonPanel.add(createButton("Return to Main Menu", "quit", this), constraints);
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: enables user to view a list of their stories
    public JPanel viewPanel() {
        JPanel viewPanel = new JPanel();
//        JList<Object> storyList;
        if (library.isEmpty()) {
            String[] storyNames = {"<No Stories to View>"};
            storyList = new JList<>(storyNames);
        } else {
//            String[] storyNames = saveable.getLibrary().getStoryNames();
            storyListModel = new DefaultListModel<>();
            for (String storyName : saveable.getLibrary().getStoryNames()) {
                storyListModel.addElement(storyName);
            }
            storyList = new JList<>(storyListModel);
        }
        storyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane storyViewer = new JScrollPane(storyList);
        storyViewer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        storyViewer.setPreferredSize(new Dimension(getScreenWidth() * 4 / 3 / 10, getScreenHeight() * 5 / 2 / 10));
        viewPanel.add(storyViewer);
        return viewPanel;
    }

    // EFFECTS: handles user selection for viewing a story
    public void selectStory() {
        if (library.getSize() != 0) {
            try {
                int index = storyList.getSelectedIndex();
                String storyName = (String) storyListModel.get(index);
                if (storyName != null) {
                    if (!storyName.equals("<Select a story>")) {
                        ViewStoryWindow view = new ViewStoryWindow(saveable, storyName);
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
        } else {
            JOptionPane.showMessageDialog(frame, createLabel("You don't have any stories."), "Library Empty No Stories",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes story from library
    private void deleteStory() {
        int index = storyList.getSelectedIndex();
        if (index != -1) {
            String storyName = (String) storyListModel.get(index);
            int delete = JOptionPane.showConfirmDialog(frame, createLabel("Are you sure you want "
                            + "to delete " + storyName + "?"),
                    "Deleting...?", JOptionPane.YES_NO_OPTION);
            if (delete == 0) {
                storyListModel.remove(index);
                Story story = saveable.getLibrary().findStory(storyName);
                saveable.getLibrary().getStories().remove(story);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: enables user to create a story for the library
    private void createStory(String storyName) {
        if (storyName != null && storyName.length() > 0) {
            storyName = removeSpaces(storyName);
            Story story = new Story(storyName, "./data/" + storyName + ".txt");
            if (library.addStory(story)) {
                storyListModel.addElement(story.getName());
                WriteStoryWindow wsw = new WriteStoryWindow(saveable, story);
                wsw.displayFrame();
//            frame.dispose();
            } else {
                String newName = JOptionPane.showInputDialog(createLabel("A story with that name already exists!"
                        + " Choose a different name:"));
                createStory(newName);
            }
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

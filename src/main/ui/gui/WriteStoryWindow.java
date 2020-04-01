package ui.gui;

import model.Story;
import model.WritingPrompt;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * a class representing a text editor to create stories for a library
 */
public class WriteStoryWindow extends Window implements ActionListener {
    //    private String storyName;
    private final Saveable saveable;
    private final Story story;
    private JTextArea textArea;

    // EFFECTS: creates a new WriteStory Window
    public WriteStoryWindow(Saveable saveable, Story story) {
        super(story.getName() + ".txt", getScreenWidth() * 5 / 10, getScreenHeight() * 5 / 10);
        this.saveable = saveable;
//        this.story = new Story(storyName, "./data/" + storyName + ".txt");
        this.story = story;
        frame.setLayout(new GridBagLayout());
        frame.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: processes action event
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "prompt":
                WritingPrompt prompt = new WritingPrompt();
                JOptionPane.showMessageDialog(frame, prompt, "Here's an idea?",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "save":
                try {
                    story.write(textArea.getText());
                    saveable.getLibrary().addStory(story);
                    saveable.write();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                break;
            case "quit":
                frame.dispose();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates frame for window
    @Override
    protected void createFrame() {
        frame.add(writePanel(), BorderLayout.NORTH);
        frame.add(buttonPanel(), BorderLayout.SOUTH);
    }

    // EFFECTS: creates panel with buttons
    public JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 0, 0);
        buttonPanel.add(createButton("Generate a Writing Prompt", "prompt", this), constraints);
        constraints.gridx = 1;
        buttonPanel.add(createButton("Save", "save", this), constraints);
        constraints.gridx = 2;
        buttonPanel.add(createButton("Cancel", "quit", this), constraints);
//        frame.add(buttonPanel, BorderLayout.SOUTH);
        return buttonPanel;
    }

    // EFFECTS: creates panel with text editor
    public JPanel writePanel() {
        JPanel writePanel = new JPanel();
        writePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        writePanel.add(createLabel("Enter your story below:"), constraints);
        constraints.gridy = 1;
        textArea = new JTextArea(5, 20);
//        test.setVisible(true);
        textArea.append("You can edit text in this box!");
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(textArea.getDocument().getLength()); //sets cursor to end automatically
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(getScreenWidth() * 3 / 10, getScreenHeight() * 3 / 10));
        writePanel.add(areaScrollPane, constraints);
//        frame.add(panel, BorderLayout.NORTH);
        return writePanel;
    }
}

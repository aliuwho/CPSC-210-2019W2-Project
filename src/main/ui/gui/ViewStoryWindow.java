package ui.gui;

import model.Library;
import model.Story;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * a class representing a window to view txt files
 */
public class ViewStoryWindow extends Window implements ActionListener {
    private final Story story;

    // EFFECTS: creates a new view story window with a story
    public ViewStoryWindow(Story story) {
        super("View Story", getScreenWidth() * 4 / 10, getScreenHeight() * 4 / 10);
//        Library library = save.getLibrary();
        this.story = story;
    }

    // MODIFIES: this
    // EFFECTS: processes action event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            frame.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates window frame
    @Override
    protected void createFrame() {
        frame.add(viewPanel());
        frame.add(createButton("Return to Desk", "return", this));
    }

    // EFFECTS: creates a panel with the story
    private JPanel viewPanel() {
        JPanel writePanel = new JPanel();
        writePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        writePanel.add(createLabel("Story: " + story.getName()), constraints);
        constraints.gridy = 1;
        JTextArea test = new JTextArea(5, 20);
        test.setEditable(false);
//        test.setVisible(true);
//        test.append("You can edit text in this box!");
        try {
            test.append(story.getStoryText());
        } catch (IOException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, createLabel("An error occurred... Please report."),
                    "ERROR STORY COULD NOT BE VIEWED", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
        }
        test.setFont(font);
        test.setLineWrap(true);
        test.setWrapStyleWord(true);
        test.setCaretPosition(test.getDocument().getLength()); //sets cursor to end automatically
        JScrollPane areaScrollPane = new JScrollPane(test);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(getScreenWidth() * 3 / 10, getScreenHeight() * 3 / 10));
        writePanel.add(areaScrollPane, constraints);
//        frame.add(panel, BorderLayout.NORTH);
        return writePanel;
    }
}

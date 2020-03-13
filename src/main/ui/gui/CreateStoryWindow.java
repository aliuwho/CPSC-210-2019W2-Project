package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStoryWindow extends Window implements ActionListener {
    private JTextField enterName;
    private String storyName;

    public CreateStoryWindow() {
        super("Create a Story", getScreenWidth() * 4 / 10, getScreenHeight() * 2 / 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enterStoryName")) {
            String input = enterName.getText();
            if (!input.equals("")) {
                storyName = enterName.getText();
                WriteStoryWindow wsw = new WriteStoryWindow(storyName);
                wsw.displayFrame();
                frame.dispose();
            } else {
                errorWindow("Not a valid story name", "Invalid. Please enter a different name!");
            }
        }
    }

    @Override
    protected void createFrame() {
        GridBagConstraints constraints = new GridBagConstraints();
        frame.add(createLabel("What would you like to call your story? (Make sure the name doesn't have spaces!"));
        constraints.gridy = 1;
        enterName = new JTextField(20);
        enterName.setFont(font);
        frame.add(enterName, constraints);
        constraints.gridy = 2;
//        frame.add(createLabel(storyName), constraints);
        constraints.gridy = 3;
        frame.add(createButton("Submit", "enterStoryName", this), constraints);
    }
}

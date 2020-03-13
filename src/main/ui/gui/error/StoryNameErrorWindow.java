package ui.gui.error;

import ui.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoryNameErrorWindow extends Window implements ActionListener {
    private String storyName;

    public StoryNameErrorWindow(String storyName) {
        super("Story Name Error", getScreenWidth() * 4 / 10, getScreenHeight() * 4 / 10);
        frame.setLocation(getScreenWidth() / 2, getScreenHeight() / 2);
        frame.setLayout(new GridBagLayout());
        this.storyName = this.storyName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("yes")) {
            yesWindow();
            frame.dispose();
        } else if (e.getActionCommand().equals("no")) {
            noWindow();
            frame.dispose();
        }
    }

    @Override
    protected void createFrame() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        frame.add(createLabel("A story with that name already exists. Overwrite?"), c);
//        c.gridy = 1;
//        frame.add(createLabel(Would you like to write a new file with the name '" + storyName + "'?"), c);
        c.gridy = 2;
        c.gridx = 0;
        frame.add(createButton("Yes", "yes", this), c);
        c.gridx = 2;
        frame.add(createButton("No", "no", this), c);
    }

    private void yesWindow() {
        JFrame frame = new JFrame("title"); // this be causing problems.
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        frame.setLocation(getScreenWidth() / 2, getScreenHeight() / 2);
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE)
        JLabel temp = createLabel("Okay, overwriting! Your story will be called " + storyName + ".");
        frame.getContentPane().add(temp,
                BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, temp.getText());
//        frame.dispose();
    }

    private void noWindow() {
        JFrame frame = new JFrame("title");
//        frame.setLocation(getScreenWidth() / 2, getScreenHeight() / 2);
        frame.getContentPane().add(createLabel("Select a different name: "), BorderLayout.CENTER);
        JTextField jtf = new JTextField(12);
        frame.getContentPane().add(jtf, BorderLayout.CENTER);
        this.storyName = jtf.getText();
//        runApp();
    }

    public String getStoryName() {
        return storyName;
    }
}


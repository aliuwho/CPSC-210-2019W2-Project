package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteStoryWindow extends Window implements ActionListener {
    private String storyName;

    public WriteStoryWindow(String storyName) {
        super(storyName + ".txt", getScreenWidth() * 5 / 10, getScreenHeight() * 5 / 10);
        frame.setLayout(new GridBagLayout());
        frame.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            frame.dispose();
        } else if (e.getActionCommand().equals("quit")) {
            frame.dispose();
        }
    }

    @Override
    protected void createFrame() {
        frame.add(writePanel(), BorderLayout.NORTH);
        frame.add(buttonPanel(), BorderLayout.SOUTH);
    }

    public JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 0, 0);
        buttonPanel.add(createButton("Save", "save", this), constraints);
        constraints.gridx = 1;
        buttonPanel.add(createButton("Cancel", "quit", this), constraints);
//        frame.add(buttonPanel, BorderLayout.SOUTH);
        return buttonPanel;
    }

    public JPanel writePanel() {
        JPanel writePanel = new JPanel();
        writePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        writePanel.add(createLabel("Enter your story below:"), constraints);
        constraints.gridy = 1;
        JTextArea test = new JTextArea(5, 20);
//        test.setVisible(true);
        test.append("You can edit text in this box!");
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

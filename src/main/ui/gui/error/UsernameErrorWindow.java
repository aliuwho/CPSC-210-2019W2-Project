package ui.gui.error;

import ui.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UsernameErrorWindow extends Window implements ActionListener {
    private String username;

    public UsernameErrorWindow(String username) {
        super("Username Error", getScreenWidth() * 4 / 10, getScreenHeight() * 4 / 10);
        frame.setLocation(getScreenWidth() / 2, getScreenHeight() / 2);
        frame.setLayout(new GridBagLayout());
        this.username = username;
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
        frame.add(createLabel("The data for that user could not be loaded."), c);
        c.gridy = 1;
        frame.add(createLabel("Would you like to write a new file with the name '" + username + "'?"), c);
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
        File file = new File("./data/" + username + ".json");
        JLabel temp;
        if (file.delete()) {

            temp = new JLabel("Okay! A new user profile has been created for " + username + ".");
//            System.out.println("Okay! A new user profile has been created for " + username + ".");
//            setFile();
        } else {
            temp = createLabel("An error occurred. Sorry... :[");
        }
        frame.getContentPane().add(temp, BorderLayout.CENTER);
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
        this.username = jtf.getText();
//        runApp();
    }

    public String getUsername() {
        return username;
    }
}

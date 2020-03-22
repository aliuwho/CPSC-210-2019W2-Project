package ui.gui;

import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;

public class UsernameWindow extends Window implements ActionListener {
    private Saveable saveable;
    private JTextField field;
    private String username;

    public UsernameWindow() {
        super("Welcome", (int) (getScreenWidth() * 0.2), (int) (getScreenHeight() * 0.15));
//        frame.setLayout(new GridBagLayout());
//        setFile();

//        this.saveable = saveable;
    }

    private void setFile() {
        File file = new File("./data/" + username + ".json");
        try {
            if (file.createNewFile()) {
                saveable = new Saveable(file.getPath(), username, LocalDateTime.now());
            } else {
                saveable = new Saveable(file.getPath());
            }
        } catch (Exception e) {
            //(ParseException | IOException e) {
            /*//TODO add window to deal with this.....
          if (input.next().equals("y")) {
                if (file.delete()) {
                    System.out.println("Okay! A new user profile has been created for " + username + ".");
                    setFile();
                } else {
                    System.out.println("An error occurred. Sorry... :[");
                }
            } else {
                System.out.println("Select a different name:");
                this.username = input.next();
                runApp();
            }*/
//            e.printStackTrace();
            dealWithError();
//            UsernameErrorWindow error = new UsernameErrorWindow(username);
//            error.displayFrame();
//            username = error.getUsername();
//            setFile();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("submit")) {
            if (!field.getText().equals("")) {
                username = field.getText();
                setFile();
                MainMenuWindow main = new MainMenuWindow(saveable);
                main.displayFrame();
                frame.dispose();
            } else {
                errorWindow("Welcome Screen", "Please enter a username!");
            }
        } else if (e.getActionCommand().equals("quit")) {
            frame.dispose();
        }
    }

    @Override
    public void createFrame() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(3, 0, 0, 15);
        constraints.weightx = 1.0;
        constraints.fill = 1;
        JLabel enterName = new JLabel("Enter your name:");
        enterName.setFont(font);
        frame.add(enterName, constraints);
        constraints.weightx = GridBagConstraints.WEST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        field = new JTextField(12);
        field.setFont(font);
        frame.add(field, constraints);
        frame.add(new JPanel(), constraints);
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        frame.add(createButton("Submit", "submit", this), constraints);
//        JButton quit = new JButton("Quit");
//        quit.setFont(font);
//        quit.setActionCommand("quit");
//        quit.addActionListener(this);
        frame.add(createButton("Quit", "quit", this), constraints);
//        displayFrame();
    }

    private void dealWithError() {
        String msg = "The data for that user could not be loaded.\nWould you like to write a new "
                + "profile with the username '" + username + "'?";
        JLabel errorLabel = createLabel(msg);
//        frame.add(createLabel("Would you like to write a new file with the name '" + username + "'?"), c);
        int overwrite = JOptionPane.showConfirmDialog(frame, errorLabel, "Username Error", JOptionPane.YES_NO_OPTION);
        if (overwrite == 0) {
            JOptionPane.showMessageDialog(frame,
                    createLabel("Okay! A new user profile has been created for " + username + "."),
                    "Overwriting...", JOptionPane.INFORMATION_MESSAGE);
        } else {
            username = JOptionPane.showInputDialog(frame, createLabel("Enter a different username:"),
                    "New Username!", JOptionPane.QUESTION_MESSAGE);
            setFile();
        }
    }


}

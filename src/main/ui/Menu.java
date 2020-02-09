package ui;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner input;
    private String appName;
    protected String username;

    // MODIFIES: THIS
    protected void setInput(Scanner input) {
        this.input = input;
    }

    // MODIFIES: this
    // EFFECTS: sets application name to appName
    protected void setName(String appName) {
        this.appName = appName;
    }

    //MODIFIES: this
    // EFFECTS: sets username to username
    protected void setUsername(String username) {
        this.username = username;
    }

    // EFFECTS: runs app for user input
    public void runApp() {
        greet();
        displayMenu();

        boolean keepGoing = true;
        String command;

        try {
            while (keepGoing) {

                command = input.next();
                command = command.toLowerCase();


                if (command.equals("q")) {
                    keepGoing = false;
                } else {
                    processCommand(command);
                }
            }
        } catch (Exception e) {
            System.out.println("This is the " + appName + " menu error.");
        }
        farewell();

    }

    // EFFECTS: processes user command wrt Application
    public abstract void processCommand(String command);

    // EFFECTS: display application menu options
    protected abstract void displayMenu();

    protected void greet() {
        System.out.println("Welcome to " + appName + ", " + username + "!");
    }

    protected abstract void farewell();

    // EFFECTS: informs user that option is not available
    public void notReady() {
        System.out.println("That option isn't available yet!");
    }

}

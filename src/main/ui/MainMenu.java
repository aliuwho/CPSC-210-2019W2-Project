package ui;

import java.util.Scanner;

public class MainMenu extends Menu {

    // EFFECTS: returns username
    public String getUsername() {
        return username;
    }

    // EFFECTS: runs the Main Menu application
    public MainMenu(Scanner input) {
        setName("Main Menu");
        setInput(input);
        runApp();
    }

    // EFFECTS: processes user command wrt Main Menu
    @Override
    public void processCommand(String command) {
        switch (command) {
            case "w":
                WritingDeskMenu w = new WritingDeskMenu();
                w.setUsername(username);
                w.setInput(input);
                w.runApp();
                displayMenu();
                break;
            case "p":
                PetRoomMenu p = new PetRoomMenu();
                p.setUsername(username);
                p.setInput(input);
                p.runApp();
                break;
            case "a":
                notReady();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: displays menu
    @Override
    protected void displayMenu() {
        if (username == null) {
            System.out.println("What's your name?");
            username = input.next();
        }
        System.out.println("\nHello, " + username + "!");
        System.out.println("Choose from:");
        System.out.println("\tw -> Writing Desk (recommended for 8 years old or older");
        System.out.println("\tp -> Pet Care");
        System.out.println("\ta -> Avatar Customization (not ready yet!)");
        System.out.println("\tq -> Quit");

    }


    @Override
    protected void farewell() {
        System.out.println("\nSee you next time! ☆");
    }
}

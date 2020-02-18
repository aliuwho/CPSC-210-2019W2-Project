package ui;

import java.io.File;
import java.util.Scanner;

public class MainMenu extends Menu {
    private WritingDeskMenu writingDeskMenu;
    private PetRoomMenu petRoomMenu;
    private File file;

    // EFFECTS: returns username
    public String getUsername() {
        return username;
    }

    // EFFECTS: runs the Main Menu application
    public MainMenu(Scanner input) {
        setName("Main Menu");
        setInput(input);
        System.out.println("What's your name?");
        this.username = input.next();
        file = new File("./data/" + username + ".json");

        writingDeskMenu = new WritingDeskMenu(file);
        //writingDeskMenu.setUsername(username);
        //writingDeskMenu.setLibrary(library);
        writingDeskMenu.setInput(input);

        petRoomMenu = new PetRoomMenu();
        //petRoomMenu.setUsername(username);
        //petRoomMenu.setInput(input);

    }

    // EFFECTS: processes user command wrt Main Menu
    @Override
    public void processCommand(String command) {
        switch (command) {
            case "w":
                writingDeskMenu.runApp();
                break;
            case "p":
                petRoomMenu.runApp();
                break;
           /* case "a":
                notReady();
                break;*/
            case "4":
                ConnectFourMenu c4 = new ConnectFourMenu();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
        displayMenu();
    }

    // EFFECTS: displays menu
    @Override
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("\tw -> Writing Desk (recommended for 8 years old or older)");
        System.out.println("\tp -> Pet Care");
        //System.out.println("\ta -> Avatar Customization (not ready yet!)");
        //System.out.println("\t4 -> Play Connect4");
        System.out.println("\tq -> Quit");

    }

    // EFFECTS: bids farewell to user
    @Override
    protected void farewell() {
        System.out.println("\nSee you next time! ☆");
    }
}

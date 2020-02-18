package ui;

import model.Saveable;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends Menu {
    private WritingDeskMenu writingDeskMenu;
    private PetRoomMenu petRoomMenu;
    //    private Library library;
    private File file;
    private Saveable saveable;

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
        try {
            if (file.length() == 0) {
                saveable = new Saveable(file.getPath(), username);
            } else {
                if (file.createNewFile()) {
                    saveable = new Saveable(file.getPath(), username);
                } else {
                    saveable = new Saveable(file.getPath());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred! Please quit and report the following:");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("The data for that user could not be loaded.");
            // add ability to delete that file entirely or create new user
        }

        writingDeskMenu = new WritingDeskMenu(saveable);
        writingDeskMenu.setInput(input);

        petRoomMenu = new PetRoomMenu();
        petRoomMenu.setUsername(username);
        petRoomMenu.setInput(input);

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
        try {
            saveable.write();
        } catch (IOException e) {
            System.out.println("An error occurred with your file:");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Something happened. oops.");
            e.printStackTrace();
        }
        System.out.println("\nSee you next time! ☆");
    }
}

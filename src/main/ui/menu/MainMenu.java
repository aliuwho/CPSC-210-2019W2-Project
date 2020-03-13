package ui.menu;

import org.json.simple.parser.ParseException;
import persistence.Saveable;
import ui.gui.MainMenuWindow;
import ui.gui.UsernameWindow;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * represents a menu for users to select from different games
 */
public class MainMenu extends Menu {
    private final File file;
    private Saveable saveable;

    // EFFECTS: runs the Main Menu application
    public MainMenu(Scanner input) {
        super(input, "Main Menu");
//        this.input = input;
        UsernameWindow user = new UsernameWindow();
        user.displayFrame();
        System.out.println("What's your name?");
        this.username = input.next();
        this.file = new File("./data/" + username + ".json");
        setFile();
    }

    private void setFile() {
        try {
            if (file.createNewFile()) {
                saveable = new Saveable(file.getPath(), username, LocalDateTime.now());
            } else {
                saveable = new Saveable(file.getPath());
            }
        } catch (ParseException | IOException e) {
            System.out.println("The data for that user could not be loaded.");
            System.out.println("Would you like to write a new file with the name '" + username + "'?");
            System.out.println("\ty -> yes\tn -> no");
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
            }

        }
    }

    // EFFECTS: processes user command wrt Main Menu
    @Override
    public void processCommand(String command) {
//        switch (command) {
//            case "u":
//                Levels levels = new Levels(saveable);
//                levels.info();
//                break;
//            case "w":
//                WritingDeskMenu writingDeskMenu = new WritingDeskMenu(saveable, input, username);
//                writingDeskMenu.runApp();
//                break;
//            case "p":
//                PetSelectMenu petSelectMenu = new PetSelectMenu(saveable, input, username);
//                petSelectMenu.runApp();
//                break;
//            case "4":
//                //  ConnectFourMenu c4 = new ConnectFourMenu();
//                break;
//            default:
//                System.out.println("Selection not valid...");
//                break;
//        }
        displayMenu();
        try {
            saveable.write();
        } catch (IOException e) {
//            e.printStackTrace();
            //do nothing lol...
        }
    }

    // EFFECTS: displays menu
    @Override
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("\tu -> User profile");
        System.out.println("\tw -> Writing Desk (recommended for 8 years old or older)");
        System.out.println("\tp -> Pet Care");
        //System.out.println("\t4 -> Play Connect4");
        System.out.println("\tq -> Quit");
        MainMenuWindow mainMenuWindow = new MainMenuWindow(saveable);
        mainMenuWindow.displayFrame();

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

package ui;

import model.Story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


// implementation of console based off TellerApp from:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// look to TellerApp.java for related code
public class GamesApp {
    private Scanner input;
    private ArrayList<String> cmds;

    // EFFECTS: runs the teller application
    public GamesApp() {
        runGames();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGames() {
        try {
            System.out.println(System.in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean keepGoing = true;
        String command;
        displayMenu();
        input = new Scanner(System.in);

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
            System.out.println("This is the bitch.");
        }

        System.out.println("\nSee you next time! ☆");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        try {
            System.out.println(System.in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (command) {
            case "w":
                createStory();
                break;
            case "p":
                playWithPet();
                break;
            case "a":
                customizeAvatar();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: runs the Writing Desk
    private void writingDesk() {
        System.out.println("Welcome to the Writing Desk, " + username + "!");
        displayWritingMenu();

        boolean keepGoing = true;
        String command;

        try {
            while (keepGoing) {

                command = input.next();
                command = command.toLowerCase();


                if (command.equals("q")) {
                    keepGoing = false;
                } else {
                    processWritingCommand(command);
                }
            }
        } catch (Exception e) {
            System.out.println("This is the Writing Menu error.");
        }
        System.out.println("Returning to main menu!");

    }

    // EFFECTS: processes user command wrt Writing Options
    private void processWritingCommand(String command) {
        switch (command) {
            case "p":
                WritingPrompt p = new WritingPrompt();
                System.out.println(p.getPrompt());
                displayWritingMenu();
                break;
            case "c":
                createStory();
                break;
            case "v":
                viewStory();
                break;
            case "e":
                editStory();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: runs the Pet Room
    private void petRoom() {
        System.out.println("Welcome to the Pet Room, " + username + "!");
        displayPetMenu();

        boolean keepGoing = true;
        String command;

        try {
            while (keepGoing) {

                command = input.next();
                command = command.toLowerCase();


                if (command.equals("q")) {
                    keepGoing = false;
                } else {
                    processPetCommand(command);
                }
            }
        } catch (Exception e) {
            System.out.println("This is the Pet Room error.");
        }
        System.out.println("Returning to main menu!");

    }

    // EFFECTS: processes user command wrt Pet Options
    private void processPetCommand(String command) {
        switch (command) {
            case "c":
                break;
            case "n":
                namePet();
                break;
            case "f":
                feedPet(1);
                break;
            case "p":
                playWithPet();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    private void feedPet(int i) {
    }

    private void namePet() {
    }

    private void editStory() {
        notReady();
    }

    private void viewStory() {
        notReady();
    }

    private void customizeAvatar() {
        notReady();
    }

    private void playWithPet() {
    }

    public void createStory() {
        try {
            System.out.println("What would you like to call your story? \n");
            String storyName = input.next();
            Story story = new Story(storyName);
            writeStory(story);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
        }
    }

    public void writeStory(Story story) {
        String substance = "\n";
        System.out.println("Enter your story here. Type 'the_end' on a new line when you're finished! \n");
        boolean writing = true;
        String storyLine;

        int num = 0;
        while (writing) {
            storyLine = input.next();
            num++;
            if (storyLine.toLowerCase().equals("the_end")) {
                story.write(substance);
                System.out.println("Your story has been saved. You wrote " + (num - 1) + " words!");
                writing = false;
            } else {
                substance += storyLine + " ";
            }
        }
        displayMenu();

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> Writing Room (recommended for 8 years old or older");
        System.out.println("\tp -> Pet Care");
        System.out.println("\ta -> Avatar Customization (not ready yet!");
        System.out.println("\tq -> Quit");
    }
}
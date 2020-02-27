package ui;

import model.Library;
import model.Story;
import model.WritingPrompt;
import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;
import persistence.Saveable;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * represents a menu for selecting writing desk options
 */
public class WritingDeskMenu extends Menu {
    private static Saveable saveable;
    private static Library library;
//    private File file;

    // EFFECTS: creates new WritingDesk app
    public WritingDeskMenu(Saveable s, Scanner input, String username) {
        setAppName("Writing Desk");
        saveable = s;
        library = s.getLibrary();
        this.input = input;
        this.username = username;
        this.username = s.getName();
    }

    // EFFECTS: prints out farewell for Writing Desk
    @Override
    protected void farewell() {
        System.out.println("Returning to main menu.");
    }

    // EFFECTS: processes user command wrt Writing Options
    @Override
    public void processCommand(String command) {
        switch (command) {
            case "p":
                WritingPrompt p = new WritingPrompt();
                System.out.println(p.getPrompt());
                break;
            case "c":
                createStory(library);
                break;
            case "v":
                selectStory();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
        displayMenu();
    }

    // EFFECTS: enables user to select a story from library
    public void selectStory() {
        try {
            System.out.println("What story you would like to view?");
            System.out.println("Here are your stories:");
            System.out.println(library.getStories());
            String title = input.next();

            System.out.println(library.getStoryText(library.findStory(title)));
        } catch (EmptyLibraryException e) {
            System.out.println("There are no stories in your library.");
        } catch (NotAStoryException e) {
            System.out.println("That's not a valid story!");
            selectStory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: displays Writing Desk options
    @Override
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("\tp -> Get a simple writing prompt");
        System.out.println("\tc -> Create a story");
        System.out.println("\tv -> View your stories");
        System.out.println("\tq -> Return to main menu");
    }

    // EFFECTS: creates a new story with a name
    private void createStory(Library l) {
        System.out.println("What would you like to call your story? \n(Make sure the name doesn't have spaces!)");
        String storyName = input.next();
        Story story = new Story(storyName, "./data/" + username + "_" + storyName + ".txt");
        File f = new File("./data/" + username + "_" + storyName + ".txt");
        try {
            if (f.createNewFile()) {
                System.out.println("Alright, your story will be called " + story.getName() + ".");
            } else if (!f.createNewFile()) {
                System.out.println("A story with that name already exists. Overwrite?\n\ty-> yes\tn ->no");
                if (input.next().equals("n")) {
                    createStory(l);
                }
            }
        } catch (IOException e) {
            System.out.println("That wasn't a valid story name! Redirecting...");
        }
        System.out.println("Alright, your story will be called " + story.getName() + ".");
        writeStory(story, l);
    }

    // MODIFIES: story
    // EFFECTS: adds user input to story
    private void writeStory(Story story, Library l) {
        String substance = "";
        System.out.println("Enter your story here. Type 'the_end' on a new line when you're finished! \n");
        boolean writing = true;

        while (writing) {
            String storyLine = input.nextLine();
            if (storyLine.toLowerCase().equals("the_end")) {
                try {
                    story.write(substance);
                } catch (IOException e) {
                    System.out.println("An error occurred when saving your story :( Please quit and report the bug.");
                }
                int num = substance.split(" |\n").length;
                System.out.println("You wrote " + (num - 1) + " words. " + story.getName() + " has been saved!");
                saveable.addPoints(num - 1);
                addStory(l, story);
                writing = false;
            } else {
                substance += storyLine + "\n";
            }
        }

    }

    // EFFECTS: runs Story Add option
    public void addStory(Library l, Story story) {
        StoryAddMenu storyAddMenu = new StoryAddMenu(input, username, story, l);
        storyAddMenu.runApp();
    }

}

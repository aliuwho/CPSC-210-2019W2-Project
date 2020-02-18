package ui;

import model.Library;
import model.Saveable;
import model.Story;
import model.WritingPrompt;
import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;
import model.exceptions.StoryNameDuplicateException;

import java.io.IOException;

public class WritingDeskMenu extends Menu {
    private Saveable saveable;
    private Library library;
//    private File file;

    // EFFECTS: creates new WritingDesk app
    public WritingDeskMenu(Saveable s) {
        setName("Writing Desk");
        saveable = s;
        this.library = s.getLibrary();
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
        try {
            System.out.println("What would you like to call your story? \n(Make sure the name doesn't have spaces!)");
            String storyName = input.next();
            Story story = new Story(storyName, "./data/" + username + "_" + storyName + ".txt");
            System.out.println("Alright, your story will be called " + story.getName() + ".");
            writeStory(story, l);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
        } catch (StoryNameDuplicateException e) {
            System.out.println("That name is already being used!");
            createStory(l);
        }
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
                saveable.setPoints((int) (saveable.getPoints() + num - 1));
                addStory(l, story);
                writing = false;
            } else {
                substance += storyLine + "\n";
            }
        }

    }

    // EFFECTS: runs Story Add option
    public void addStory(Library l, Story story) {
        StoryAddMenu storyAddMenu = new StoryAddMenu(story, l);
        storyAddMenu.setInput(input);
        storyAddMenu.setUsername(username);
        storyAddMenu.runApp();
    }

//    // EFFECTS: sets the library as l
//    public void setLibrary(Library l) {
//        library = l;
//    }
}

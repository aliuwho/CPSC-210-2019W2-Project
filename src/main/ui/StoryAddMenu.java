package ui;

import model.Library;
import model.Story;

public class StoryAddMenu extends Menu {
    private Library library;
    private Story story;

    // EFFECTS: creates a new StoryAdd menu with a story
    public StoryAddMenu(Story story) {
        setName("StoryAdd Menu");
        this.story = story;
    }

    // EFFECTS: welcomes user
    @Override
    protected void welcome() {
        System.out.println(username + ", would you like to save your story for viewing?");
    }

    // EFFECTS: processes command for StoryAddMenu
    @Override
    public void processCommand(String command) {
        if ("y".equals(command)) {
            library.addStory(story);
            System.out.println("Your story has been added to your library! Press q to return.");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: bids farewell to user
    @Override
    protected void farewell() {
        System.out.println("Returning to Writing Desk...");
    }

    // EFFECTS: displays StoryAddMenu options
    @Override
    public void displayMenu() {
        super.displayMenu();
        System.out.println("\ty -> Yes, add my story to my library");
        System.out.println("\tq -> Don't add my story to my library");
    }

    // EFFECTS: sets the library as l
    public void setLibrary(Library l) {
        library = l;
    }
}

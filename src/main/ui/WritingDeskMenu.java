package ui;

import model.Library;
import model.Story;
import model.WritingPrompt;

public class WritingDeskMenu extends Menu {
    private Library library;

    // EFFECTS: creates new WritingDesk app
    public WritingDeskMenu() {
        setName("Writing Desk");
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
                displayMenu();
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
        System.out.println("Here are your stories:");
        library.getStories();
        System.out.println("What story you would like to view?");
        String title = input.next();
        library.viewStory(library.findStory(title));
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
            System.out.println("What would you like to call your story? \n");
            String storyName = input.next();
            Story story = new Story(storyName);
            writeStory(story, l);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
        }
    }

    // MODIFIES: story
    // EFFECTS: adds user input to story
    private void writeStory(Story story, Library l) {
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
                System.out.println("You wrote " + (num - 1) + " words. " + story.getName() + " has been saved!");
                StoryAddMenu storyAddMenu = new StoryAddMenu(story);
                storyAddMenu.setLibrary(l);
                storyAddMenu.setInput(input);
                storyAddMenu.setUsername(username);
                storyAddMenu.runApp();
                writing = false;
            } else {
                substance += storyLine + " ";
            }
        }

    }

    // EFFECTS: sets the library as l
    public void setLibrary(Library l) {
        library = l;
    }
}

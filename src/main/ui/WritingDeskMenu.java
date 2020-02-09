package ui;

import model.Story;
import model.WritingPrompt;

public class WritingDeskMenu extends Menu {

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

    @Override
    protected void displayMenu() {
        System.out.println("Choose from:");
        System.out.println("\tp -> Get a simple writing prompt");
        System.out.println("\tc -> Create a story");
        System.out.println("\tq -> Return to main menu");
    }

    // EFFECTS: creates a new story with a name
    private void createStory() {
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

    // MODIFIES: story
    // EFFECTS: adds user input to story
    private void writeStory(Story story) {
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

    // TODO: implement notReady methods
    private void editStory() {
        notReady();
    }

    private void viewStory() {
        notReady();
    }
}

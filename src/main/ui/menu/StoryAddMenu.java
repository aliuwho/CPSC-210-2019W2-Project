//package ui.menu;
//
//import model.Library;
//import model.Story;
//
//import java.io.File;
//import java.util.Scanner;
//
///**
// * represents a menu for adding a story to a library
// */
//public class StoryAddMenu extends Menu {
//    private final Library library;
//    private final Story story;
//
//    // EFFECTS: creates a new StoryAdd menu with a story
//    public StoryAddMenu(Scanner input, String username, Story story, Library library) {
//        super(input, "StoryAdd Menu");
////        this.input = input;
//        this.username = username;
//        this.story = story;
//        this.library = library;
//    }
//
//    // EFFECTS: welcomes user
//    @Override
//    protected void welcome() {
//        System.out.println(username + ", would you like to save your story for viewing?");
//    }
//
//    // EFFECTS: processes command for StoryAddMenu
//    @Override
//    public void processCommand(String command) {
//        if ("y".equals(command)) {
//            library.addStory(story);
//            System.out.println("Your story has been added to your library! Press q to return.");
//        } else if ("n".equals(command)) {
//            System.out.println("Alright, your story will be deleted. One moment...");
//            File f1 = new File(story.getPath());
//            if (f1.delete()) {
//                System.out.println("Your story has been deleted.");
//            }
//        } else {
//            System.out.println("Selection not valid...");
//        }
//        System.out.println("Press 'q' to return to the Writing Desk.");
//    }
//
//    // EFFECTS: bids farewell to user
//    @Override
//    protected void farewell() {
//        System.out.println("Returning to Writing Desk...");
//    }
//
//    // EFFECTS: displays StoryAddMenu options
//    @Override
//    public void displayMenu() {
//        super.displayMenu();
//        System.out.println("\ty -> Yes, add my story to my library");
//        System.out.println("\tn -> No, don't add my story to my library");
////        System.out.println("\tq -> Don't add my story to my library");
//    }
//
//// --Commented out by Inspection START (2/18/20, 10:04 AM):
////    // EFFECTS: sets the library as l
////    public void setLibrary(Library l) {
////        library = l;
////    }
//// --Commented out by Inspection STOP (2/18/20, 10:04 AM)
//}

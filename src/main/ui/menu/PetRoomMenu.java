//package ui.menu;
//
//import model.exceptions.NotHungryException;
//import model.exceptions.NotTiredException;
//import model.exceptions.TiredException;
//import model.pets.*;
//import persistence.Saveable;
//
//import java.util.Scanner;
//
///**
// * represents a menu for interacting with a pet
// */
//public class PetRoomMenu extends Menu {
//    private final Pet pet;
////    private final Saveable saveable;
//
//    // EFFECTS: creates a new PetRoom Menu
//    public PetRoomMenu(Scanner input, String username, Saveable s, Pet pet) {
//        super(input, "Pet Room");
//        this.saveable = s;
////        this.input = input;
//        this.username = username;
//        this.pet = pet;
//    }
//
//    // EFFECTS: processes command for Pet Room
//    @Override
//    public void processCommand(String command) {
//        switch (command) {
//            case "c":
//                checkStatus();
//                break;
//            case "f":
//                feedPet();
//                break;
//            case "p":
//                playWithPet();
//                break;
//            case "n":
//                napWithPet();
//                break;
//            default:
//                System.out.println("Selection not valid...");
//                break;
//        }
//        displayMenu();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: if pet is not tired, play with pet and make it tired and hungry.
//    //          otherwise, don't play
//    private void playWithPet() {
//        try {
//            pet.play();
//            speak();
//            System.out.println(pet.getName() + " enjoyed playing with you!");
//        } catch (TiredException e) {
//            System.out.println(pet.getName() + " is too tired to play. Try taking a nap!");
//        }
//    }
//
//    // EFFECTS: makes animal noises appropriate for each pet
//    private void speak() {
//        String speak = "";
//        switch (pet.getSpecies()) {
//            case "bird":
//                speak = "Chirp chirp!";
//                break;
//            case "cat":
//                speak = "Meow meow!";
//                break;
//            case "dog":
//                speak = "Woof woof!";
//                break;
//            case "horse":
//                speak = "Neigh neigh!";
//                break;
//            case "lizard":
//                speak = "The lizard can't say anything, but if it could, it would tell you it loves you!";
//                break;
//        }
//        System.out.println(speak);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: if pet is tired, take nap with pet and make it not tired.
//    //          otherwise, don't nap
//    private void napWithPet() {
//        try {
//            pet.sleep();
//            System.out.println("You and " + pet.getName() + " took a lovely nap. You both feel well-rested!");
//        } catch (NotTiredException e) {
//            System.out.println(pet.getName() + " isn't tired! " + pet.getName() + " wants to play!");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: if pet is hungry, feed pet and make it not hungry.
//    //          otherwise, don't feed
//    private void feedPet() {
//        try {
//            pet.feed();
//            System.out.println(pet.getName() + " enjoyed the meal! Yummy!");
//        } catch (NotHungryException e) {
//            System.out.println(pet.getName() + " isn't hungry!");
//        }
//    }
//
//    // EFFECTS: shows the status of the animal
//    public void checkStatus() {
//        String h;
//        if (pet.isHungry()) {
//            h = "hungry";
//        } else {
//            h = "full";
//        }
//
//        String t;
//        if (pet.isTired()) {
//            t = "tired";
//        } else {
//            t = "feeling energetic";
//        }
//        String msg = pet.getName() + " is " + h + " and " + t + ".\n";
//        System.out.println(msg);
//        petIsSad();
//    }
//
//    // EFFECTS: informs user if pet is hungry and tired; otherwise,
//    //          checks if pet is happy
//    public void petIsSad() {
//        if (pet.isSad()) {
//            System.out.println(pet.getName() + " is a little sad... Make sure to feed them and let them rest!\n");
//        } else {
//            petIsHappy();
//        }
//    }
//
//    // EFFECTS: if pet is not hungry and not tired, it performs a special action;
//    //          otherwise, informs user that pet is doing okay
//    public void petIsHappy() {
//        String msg = "";
//        if (pet.isHappy()) {
//            if (pet instanceof Lizard) {
//                msg = pet.getName() + " hugs your finger. You are taking good care of your lizard!";
//            } else if (pet instanceof Horse) {
//                msg = pet.getName() + " does a happy dance. You are taking good care of your horse!";
//            } else if (pet instanceof Dog) {
//                msg = pet.getName() + " excitedly licks your face. You are taking good care of your dog!";
//            } else if (pet instanceof Cat) {
//                msg = pet.getName() + " purrs happily. You are taking good care of your cat!";
//            } else if (pet instanceof Bird) {
//                msg = pet.getName() + " whistles a happy tune. You are taking good care of your bird!";
//            }
//
//            msg += "\nYou earned 50 points!";
//            saveable.addPoints(50);
//        } else {
//            msg = pet.getName() + " is doing okay.";
//        }
//        System.out.println(msg);
//    }
//
//    // EFFECTS: displays pet room options
//    @Override
//    protected void displayMenu() {
//        if (pet == null) {
//            System.out.println("That's not a pet!");
//        }
//        super.displayMenu();
//        System.out.println("\tc -> Check on your pet");
//        System.out.println("\tf -> Feed your pet");
//        System.out.println("\tp -> Play with your pet");
//        System.out.println("\tn -> Nap with your pet");
//        System.out.println("\tq -> Return to pet selection");
//    }
//
//    // EFFECTS: end of program message to user
//    @Override
//    protected void farewell() {
//        System.out.println("Returning to main menu.");
//    }
//
//    // MODIFIES: this
//    // EFFECTS:
//}

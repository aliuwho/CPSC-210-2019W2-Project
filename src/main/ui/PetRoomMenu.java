package ui;

import model.animals.*;
import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;

import java.util.Scanner;

public class PetRoomMenu extends Menu {
    //private ArrayList<Animal> pets;
    private Animal pet;

    // EFFECTS: creates a new PetRoom Menu
    public PetRoomMenu() {
        setName("Pet Room");
        //pets = new ArrayList<>();
    }

    // EFFECTS: processes command for Pet Room
    @Override
    public void processCommand(String command) {
        switch (command) {
            case "c":
                pet.status();
                break;
            case "f":
                feedPet();
                break;
            case "p":
                playWithPet();
                break;
            case "n":
                napWithPet();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
        displayMenu();
    }

    // EFFECTS: allows user to select a pet
    private void choosePet(Scanner input) {
        System.out.println("What animal would you like to be your pet?");
        PetSelectMenu ps = new PetSelectMenu();
        ps.setInput(input);
        ps.setUsername(username);
        ps.runApp();
        pet = ps.getPet();
    }

    // MODIFIES: this
    // EFFECTS: if pet is not tired, play with pet and make it tired and hungry.
    //          otherwise, don't play
    private void playWithPet() {
        try {
            pet.play();
        } catch (TiredException e) {
            System.out.println(pet.getName() + " is too tired to play. Try taking a nap!");
        }
    }

    // MODIFIES: this
    // EFFECTS: if pet is tired, take nap with pet and make it not tired.
    //          otherwise, don't nap
    private void napWithPet() {
        try {
            pet.sleep();
        } catch (NotTiredException e) {
            System.out.println(pet.getName() + " isn't tired! " + pet.getName() + " wants to play!");
        }
    }

    // MODIFIES: this
    // EFFECTS: if pet is hungry, feed pet and make it not hungry.
    //          otherwise, don't feed
    private void feedPet() {
        try {
            pet.feed();
        } catch (NotHungryException e) {
            System.out.println(pet.getName() + " isn't hungry!");
        }
    }

    // EFFECTS: displays pet room options
    @Override
    protected void displayMenu() {
        if (pet == null) {
            choosePet(input);
        }
        super.displayMenu();
        System.out.println("\tc -> Check on your pet");
        System.out.println("\tf -> Feed your pet");
        System.out.println("\tp -> Play with your pet");
        System.out.println("\tn -> Nap with your pet");
        System.out.println("\tq -> Return to main menu");
    }

    // EFFECTS: end of program message to user
    @Override
    protected void farewell() {
        System.out.println("Returning to main menu.");
    }
}

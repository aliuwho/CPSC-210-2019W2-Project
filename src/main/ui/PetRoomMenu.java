package ui;

import model.animals.Animal;

import java.util.Scanner;

public class PetRoomMenu extends Menu {
    //private ArrayList<Animal> pets;
    private Animal pet;

    public PetRoomMenu() {
        setName("Pet Room");
        //pets = new ArrayList<>();
    }

    @Override
    public void processCommand(String command) {
        switch (command) {
            case "c":
                choosePet(input);
                displayMenu();
                break;
            case "n":
                namePet();
                displayMenu();
                break;
            case "f":
                feedPet();
                break;
            case "p":
                playWithPet();
                break;
            case "s":
                napWithPet();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    private void choosePet(Scanner input) {
        System.out.println("What animal would you like to be your pet?");
        PetSelectMenu ps = new PetSelectMenu();
        ps.setInput(input);
        ps.setUsername(username);
        ps.runApp();
    }

    private void playWithPet() {
        notReady();
    }

    private void napWithPet() {

    }

    private void feedPet() {
        notReady();
    }

    private void namePet() {
        notReady();
    }

    @Override
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("\tc -> Choose an animal");
        System.out.println("\tn -> Name your animal");
        System.out.println("\tf -> Feed your animal");
        System.out.println("\tp -> Play with your animal");
        System.out.println("\tq -> Return to main menu");
    }

    @Override
    protected void farewell() {
        System.out.println("Returning to main menu.");
    }
}

package ui;

import model.Animal;

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

    private void feedPet(int i) {
        notReady();
        i = i + 1;
    }

    private void namePet() {
        notReady();
    }

    @Override
    protected void displayMenu() {
        System.out.println("Choose from:");
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

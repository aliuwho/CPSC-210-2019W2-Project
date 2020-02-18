package ui;

import model.animals.*;

public class PetSelectMenu extends Menu {
    private Animal pet;
 //   private String animalType;

    // EFFECTS: creates a new PetSelectMenu
    public PetSelectMenu() {
        setName("Pet Selection");
    }

    // EFFECTS: returns pet
    public Animal getPet() {
        return pet;
    }

    // EFFECTS: displays pet select options
    @Override
    public void processCommand(String command) {
        String name = namePet();
        System.out.println("Press 'q' to do things with your pet!");
        switch (command) {
            case "b":
                pet = new Bird(name);
                break;
            case "c":
                pet = new Cat(name);
                break;
            case "d":
                pet = new Dog(name);
                break;
            case "h":
                pet = new Horse(name);
                break;
            case "l":
                pet = new Lizard(name);
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: displays pet options
    @Override
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("\tb -> Bird");
        System.out.println("\tc -> Cat");
        System.out.println("\td -> Dog");
        System.out.println("\th -> Horse");
        System.out.println("\tl -> Lizard");
        System.out.println("\tq -> Quit");
    }

    // EFFECTS: end of program message to user
    @Override
    protected void farewell() {
        System.out.println("You have selected a pet!");
    }

    // EFFECTS: names pet
    private String namePet() {
        System.out.println("What would you like to call your pet? (Make it one word!)");
        return input.next();
    }
}

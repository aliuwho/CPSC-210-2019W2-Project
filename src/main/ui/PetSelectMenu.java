package ui;

import model.animals.Animal;

public class PetSelectMenu extends Menu {
    private Animal pet;

    public PetSelectMenu() {
        setName("Pet Selection");
    }

    // EFFECTS: displays pet select options
    @Override
    public void processCommand(String command) {
        switch (command) {
            case "b":
                //pet = new Bird();
                break;
            case "c":
                break;
            case "d":
                break;
            case "h":
                break;
            case "l":
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

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

    @Override
    protected void farewell() {
        System.out.println("You have selected a pet!");
    }
}

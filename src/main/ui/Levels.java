package ui;

import persistence.Saveable;

/**
 * represents user profile display
 */
public class Levels {
    private final Saveable saveable;

    // EFFECTS: creates a new Levels
    public Levels(Saveable saveable) {
        this.saveable = saveable;
    }

    // EFFECTS: prints level information
    public void info() {
        System.out.println("Your account was created at " + saveable.getStart() + ".");
        System.out.println("You have " + saveable.getPoints() + " points!");
        System.out.println("You have " + saveable.getPets().size() + " pets :]");
    }
}

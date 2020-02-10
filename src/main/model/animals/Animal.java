package model.animals;

import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;

public abstract class Animal {
    protected boolean hungry;
    protected String name;
    protected boolean tired;

    // EFFECTS: creates a new animal with a name which is:
    //          - hungry
    //          - not tired
    public Animal(String name) {
        this.name = name;
        this.hungry = true;
        this.tired = false;
    }

    // MODIFIES: this
    // EFFECTS: if hungry, returns true and animal is fed.
    //          otherwise, returns false
    public void feed() throws NotHungryException {

    }

    // EFFECTS: makes an animal noise
    public abstract void speak();

    // MODIFIES: this
    // EFFECTS: if the animal is not tired,
    //          playing makes the animal tired.
    //          otherwise, the animal will refuse to play.
    public void play() throws TiredException {
        if (isTired()) {
            throw new TiredException();
        } else {
            tired = true;
            System.out.println(name + " enjoyed playing with you!");
        }

    }

    // EFFECTS: returns true if the animal is hungry; false otherwise
    public boolean isHungry() {
        return hungry;
    }

    // EFFECTS: returns true if the animal is tired; false otherwise
    public boolean isTired() {
        return tired;
    }

    // REQUIRES: animal is tired
    // MODIFIES: this
    // EFFECTS: animal is not tired
    public void sleep() throws NotTiredException {
        tired = false;
    }

    // EFFECTS: shows the status of the animal
    public void status() {
        String h;
        if (isHungry()) {
            h = "hungry";
        } else {
            h = "not hungry";
        }

        String t;
        if (isTired()) {
            t = "tired";
        } else {
            t = "not tired";
        }

        System.out.println(name + " is " + h + " and " + t + ".");
    }
}
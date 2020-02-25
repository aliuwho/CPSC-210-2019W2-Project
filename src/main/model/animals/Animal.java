package model.animals;

import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;

public abstract class Animal {
    protected boolean hungry;
    protected final String name;
    protected boolean tired;
    protected String species;

    // EFFECTS: creates a new animal with a name which is:
    //          - hungry
    //          - not tired
    public Animal(String name) {
        this.name = name;
        this.hungry = true;
        this.tired = false;
        this.species = null;
    }

    // EFFECTS: creates a new animal with a name with a certain hunger and energy status
    public Animal(String name, boolean hungry, boolean tired) {
        this.name = name;
        this.hungry = hungry;
        this.tired = tired;
        this.species = null;
    }

    // EFFECTS: returns the pet's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns pet's species
    public String getSpecies() {
        return species;
    }

//    // MODIFIES: this
//    // EFFECTS: sets pet species as species
//    public void setSpecies() {
//        return species;
//    }

    // EFFECTS: returns true if the animal is hungry; false otherwise
    public boolean isHungry() {
        return hungry;
    }

    // EFFECTS: returns true if the animal is tired; false otherwise
    public boolean isTired() {
        return tired;
    }

    // EFFECTS: makes an animal noise
    public abstract String speak();

    // MODIFIES: this
    // EFFECTS: if animal is tired,
    //          animal becomes well-rested;
    //          otherwise, throws NotTiredException
    public void sleep() throws NotTiredException {
        if (isTired()) {
            tired = false;
        } else {
            throw new NotTiredException();
        }
    }

    // MODIFIES: this
    // EFFECTS: if hungry, animal is fed.
    //          otherwise, throws NotHungryException
    public void feed() throws NotHungryException {
        if (isHungry()) {
            hungry = false;
        } else {
            throw new NotHungryException();
        }

    }

    // MODIFIES: this
    // EFFECTS: if the animal is not tired,
    //          playing makes the animal tired.
    //          otherwise, throw TiredException
    public void play() throws TiredException {
        if (isTired()) {
            throw new TiredException();
        } else {
            tired = true;
            hungry = true;
        }
    }


    // EFFECTS: returns true if the Animal is not hungry and not tired;
    //          false otherwise
    public boolean isHappy() {
        return (!isHungry()) && (!isTired());
    }

    // EFFECTS: returns true if the Animal is hungry and tired;
    //          false otherwise
    public boolean isSad() {
        return (isHungry()) && (isTired());
    }


}

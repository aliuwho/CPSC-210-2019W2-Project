package model.animals;

/**
 * Represents a cat animal
 */
public class Cat extends Animal {

    // EFFECTS: constructs a cat with a name with a certain hunger and energy status
    public Cat(String name, boolean hungry, boolean tired) {
        super(name, hungry, tired);
        species = "cat";
    }

    // EFFECTS: creates a hungry, not tired cat with a name
    public Cat(String name) {
        super(name);
        species = "cat";
    }

    // EFFECTS: prints out cat noises
    @Override
    public String speak() {
        return "Meow meow!";
    }
}

package model.animals;

/**
 * represents a dog animal
 */
public class Dog extends Animal {

    // EFFECTS: constructs a dog with a name with a certain hunger and energy status
    public Dog(String name, boolean hungry, boolean tired) {
        super(name, hungry, tired);
        species = "dog";
    }

    // EFFECTS: creates a hungry, not tired dog with a name
    public Dog(String name) {
        super(name);
        species = "dog";
    }

    // EFFECTS: prints dog noises
    @Override
    public String speak() {
        return "Woof woof!";
    }

}

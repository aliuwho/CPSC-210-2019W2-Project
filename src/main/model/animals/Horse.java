package model.animals;

public class Horse extends Animal {

    // EFFECTS: constructs a horse with a name with a certain hunger and energy status
    public Horse(String name, boolean hungry, boolean tired) {
        super(name, hungry, tired);
        species = "horse";
    }

    // EFFECTS: creates a hungry, not tired horse with a name
    public Horse(String name) {
        super(name);
        species = "horse";
    }

    // EFFECTS: prints horse noises
    @Override
    public String speak() {
        return "Neigh neigh!";
    }
}

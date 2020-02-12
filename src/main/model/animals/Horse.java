package model.animals;

public class Horse extends Animal {

    // EFFECTS: constructs a horse with a name which is:
    //          - hungry
    //          - not tired
    public Horse(String name) {
        super(name);
    }

    // EFFECTS: prints horse noises
    @Override
    public String speak() {
        return "Neigh neigh!";
    }
}

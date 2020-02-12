package model.animals;

public class Bird extends Animal {

    // EFFECTS: constructs a bird with a name which is:
    //          - hungry
    //          - not tired
    public Bird(String name) {
        super(name);
    }

    // EFFECTS: prints bird noises
    @Override
    public String speak() {
        return "Chirp chirp!";
    }
}

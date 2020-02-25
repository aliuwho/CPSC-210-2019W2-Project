package model.animals;

public class Bird extends Animal {

    // EFFECTS: constructs a bird with a name with a certain hunger and energy status
    public Bird(String name, boolean hungry, boolean tired) {
        super(name, hungry, tired);
        species = "bird";
    }

    // EFFECTS: creates a hungry, not tired bird with a name
    public Bird(String name) {
        super(name);
        species = "bird";
    }


    // EFFECTS: prints bird noises
    @Override
    public String speak() {
        return "Chirp chirp!";
    }
}

package model.animals;

public class Lizard extends Animal {

    // EFFECTS: constructs a lizard with a name with a certain hunger and energy status
    public Lizard(String name, boolean hungry, boolean tired) {
        super(name, hungry, tired);
        species = "lizard";
    }

    // EFFECTS: creates a hungry, not tired lizard with a name
    public Lizard(String name) {
        super(name);
        species = "lizard";
    }

    // EFFECTS: prints lizard noises...
    @Override
    public String speak() {
        return "The lizard can't say anything, but if it could, it would tell you it loves you!";
    }
}

package model.animals;

public class Lizard extends Animal {

    // EFFECTS: constructs a lizard with a name which is:
    //          - hungry
    //          - not tired
    public Lizard(String name) {
        super(name);
    }

    // EFFECTS: prints lizard noises...
    @Override
    public String speak() {
        return "The lizard can't say anything, but if it could, it would tell you it loves you!";
    }
}

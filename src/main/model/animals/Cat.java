package model.animals;

public class Cat extends Animal {

    // EFFECTS: constructs a cat with a name which is:
    //          - hungry
    //          - not tired
    public Cat(String name) {
        super(name);
    }

    // EFFECTS: prints out cat noises
    @Override
    public String speak() {
        return "Meow meow!";
    }
}

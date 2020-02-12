package model.animals;

public class Dog extends Animal {

    // EFFECTS: constructs a bird with a name which is:
    //          - hungry
    //          - not tired
    public Dog(String name) {
        super(name);
    }

    // EFFECTS: prints dog noises
    @Override
    public String speak() {
        return "Woof woof!";
    }

}

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
    public void speak() {
        System.out.println("Neigh neigh!");
    }

    // EFFECTS: shows horse status
    @Override
    public void status() {
        super.status();
        if (!isHungry() && !isTired()) {
            System.out.print(name + " does a happy dance. You are taking good care of your horse!");
        }
    }
}

package model.animals;

public class Bird extends Animal {

    // EFFECTS: constructs a bird with a name which is:
    //          - hungry
    //          - not tired
    public Bird(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println("Chirp chirp!");
    }

    @Override
    public void status() {
        super.status();
        if (!isHungry() && !isTired()) {
            System.out.print(name + " whistles a happy tune. You are taking good care of your bird!");
        }
    }

}
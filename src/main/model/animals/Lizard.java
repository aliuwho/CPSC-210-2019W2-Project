package model.animals;

public class Lizard extends Animal {

    // EFFECTS: constructs a lizard with a name which is:
    //          - hungry
    //          - not tired
    public Lizard(String name) {
        super(name);
    }

    // EFFECTS: prints lizard noises :>
    @Override
    public void speak() {
        System.out.println("The lizard can't say anything, but if it could, it would tell you it loves you!");
    }

    // EFFECTS: shows lizard status
    @Override
    public void status() {
        super.status();
        if (!isHungry() && !isTired()) {
            System.out.println(name + " hugs your finger. You are taking good care of your lizard!");
        }
    }
}

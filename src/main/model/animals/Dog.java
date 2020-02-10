package model.animals;

public class Dog extends Animal {

    // EFFECTS: constructs a bird with a name which is:
    //          - hungry
    //          - not tired
    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println("Woof woof!");
    }

    @Override
    public void status() {
        super.status();
        if (!isHungry() && !isTired()) {
            System.out.print(name + " excitedly licks your face. You are taking good care of your dog!");
        }
    }
}
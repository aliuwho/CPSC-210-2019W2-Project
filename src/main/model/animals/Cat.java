package model.animals;

public class Cat extends Animal {

    // EFFECTS: constructs a cat with a name which is:
    //          - hungry
    //          - not tired
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println("Meow meow!");
    }

    @Override
    public void status() {
        super.status();
        if (!isHungry() && !isTired()) {
            System.out.print(name + " purrs happily. You are taking good care of your cat!");
        }
    }
}

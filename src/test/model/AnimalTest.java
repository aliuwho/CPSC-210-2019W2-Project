package model;

import model.animals.*;
import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private Animal bird;
    private Animal cat;
    private Animal dog;
    private Animal horse;
    private Animal lizard;

    @BeforeEach
    public void runBefore() {
        bird = new Bird("Astryd");
        bird.status();

        cat = new Cat("Rai");
        cat.status();

        dog = new Dog("Hachi");
        dog.status();

        horse = new Horse("Billy");
        horse.status();

        lizard = new Lizard("Mocha");
        lizard.status();

    }

    @Test
    public void testFeedWithException() {
        assertTrue(bird.isHungry());
        try {
            bird.feed();
        } catch (NotHungryException e) {
            fail();
        }
        assertFalse(bird.isHungry());
        try {
            bird.feed();
            fail();
        } catch (NotHungryException e) {
            System.out.println("error caught!");
        }
    }

    @Test
    public void testSpeak() {
        bird.speak();
        cat.speak();
        dog.speak();
        horse.speak();
        lizard.speak();
    }

    @Test
    public void testPlayWithoutException() {
        try {
            cat.play();
        } catch (TiredException e) {
            fail();
        }
    }

    @Test
    public void testPlayWithExceptions() {
        try {
            cat.play();
        } catch (TiredException e) {
            fail();
        }

        try {
            cat.play();
            fail();
        } catch (TiredException e) {
            System.out.println("error caught!");
        }
    }

    @Test
    public void testSleepWithoutException() {
        try {
            lizard.sleep();
        } catch (NotTiredException e) {
            fail();
        }
    }

    @Test
    public void testSleepWithExceptions() {
        try {
            lizard.sleep();
        } catch (NotTiredException e) {
            fail();
        }

        try {
            lizard.sleep();
            fail();
        } catch (NotTiredException e) {
            System.out.println("error caught!");
        }
    }
}

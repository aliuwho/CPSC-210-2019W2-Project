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
        assertEquals("Astryd",bird.getName());

        cat = new Cat("Rai");
        assertEquals("Rai",cat.getName());

        dog = new Dog("Hachi");
        assertEquals("Hachi",dog.getName());

        horse = new Horse("Billy");
        assertEquals("Billy",horse.getName());

        lizard = new Lizard("Mocha");
        assertEquals("Mocha",lizard.getName());

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
            lizard.play();
            lizard.sleep();
        } catch (NotTiredException | TiredException e) {
            fail();
        }
    }

    @Test
    public void testSleepWithExceptions() {
        try {
            lizard.sleep();
            fail();
        } catch (NotTiredException e) {
            System.out.print("error caught!");
        }
    }

    @Test
    public void testHappyStatus() {
        try {
            bird.feed();
            assertTrue(bird.isHappy());

            cat.feed();
            assertTrue(cat.isHappy());

            dog.feed();
            assertTrue(dog.isHappy());

            horse.feed();
            assertTrue(horse.isHappy());

            lizard.feed();
            assertTrue(lizard.isHappy());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSadStatus() {
        try {
            bird.play();
            assertTrue(bird.isSad());

            cat.play();
            assertTrue(cat.isSad());

            dog.play();
            assertTrue(dog.isSad());

            horse.play();
            assertTrue(horse.isSad());

            lizard.play();
            assertTrue(lizard.isSad());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testBetweenStatus() {
        try {
            bird.play();
            bird.feed();
            assertFalse(bird.isHungry());
            assertFalse(bird.isSad());

            cat.play();
            cat.feed();
            assertFalse(cat.isSad());
            assertFalse(cat.isHappy());

            dog.play();
            dog.feed();
            assertFalse(dog.isSad());
            assertFalse(dog.isHappy());

            horse.play();
            horse.feed();
            assertFalse(horse.isSad());
            assertFalse(horse.isHappy());

            lizard.play();
            lizard.feed();
            assertFalse(lizard.isHappy());
            assertFalse(lizard.isSad());

        } catch (Exception e) {
            fail();
        }
    }
}

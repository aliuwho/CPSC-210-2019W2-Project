package model;

import model.pets.*;
import model.exceptions.NotHungryException;
import model.exceptions.NotTiredException;
import model.exceptions.TiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {
    private Pet bird;
    private Pet cat;
    private Pet dog;
    private Pet horse;
    private Pet lizard;

    @BeforeEach
    public void runBefore() {
        bird = new Bird("Astryd");
        assertEquals("Astryd", bird.getName());
        assertEquals("bird", bird.getSpecies());
        assertTrue(bird.isHungry());
        assertFalse(bird.isTired());

        cat = new Cat("Rai");
        assertEquals("Rai", cat.getName());
        assertEquals("cat", cat.getSpecies());
        assertTrue(cat.isHungry());
        assertFalse(cat.isTired());

        dog = new Dog("Hachi");
        assertEquals("Hachi", dog.getName());
        assertEquals("dog", dog.getSpecies());
        assertTrue(dog.isHungry());
        assertFalse(dog.isTired());

        horse = new Horse("Billy");
        assertEquals("Billy", horse.getName());
        assertEquals("horse", horse.getSpecies());
        assertTrue(horse.isHungry());
        assertFalse(horse.isTired());

        lizard = new Lizard("Mocha");
        assertEquals("Mocha", lizard.getName());
        assertEquals("lizard", lizard.getSpecies());
        assertTrue(lizard.isHungry());
        assertFalse(lizard.isTired());

    }

    @Test
    public void testOtherConstructor() {
        Bird bird1 = new Bird("Astryd", true, true);
//        assertEquals("Astryd", bird1.getName());
        assertTrue(bird1.isSad());

        Cat cat1 = new Cat("Rai", true, false);
//        assertEquals("Rai", cat.getName());
        assertTrue(cat1.isHungry());
        assertFalse(cat1.isTired());

        Dog dog1 = new Dog("Hachi", false, false);
//        assertEquals("Hachi", dog.getName());
        assertTrue(dog1.isHappy());
//        assertFalse(dog.isTired());

        Horse horse1 = new Horse("Billy", false, true);
//        assertEquals("Billy", horse.getName());
        assertFalse(horse1.isHungry());
        assertTrue(horse1.isTired());

        Lizard lizard1 = new Lizard("Mocha", true, false);
//        assertEquals("Mocha", lizard.getName());
        assertTrue(lizard1.isHungry());
        assertFalse(lizard1.isTired());
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
            dog.sleep();
            assertFalse(dog.isSad());
            assertFalse(dog.isHappy());

            horse.play();
            horse.sleep();
            assertFalse(horse.isSad());
            assertFalse(horse.isHappy());

            lizard.play();
            lizard.sleep();
            assertFalse(lizard.isHappy());
            assertFalse(lizard.isSad());

        } catch (Exception e) {
            fail();
        }
    }
}

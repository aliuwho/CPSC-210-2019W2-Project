package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void runBefore() {
        library = new Library();
    }

    @Test
    public void testAdd1Story() {
        library.addStory(new Story("story"));
        library.getStories();
        assertEquals(1, library.size());
    }

    @Test
    public void testAddMultiStory() {
        library.addStory(new Story("story"));
        library.addStory(new Story("story_1"));
        library.getStories();
        assertEquals(2, library.size());
    }

    @Test
    public void testViewStory() {
        Story story = new Story("story");
        library.addStory(story);
        library.addStory(new Story("story_1"));
        library.viewStory(story);
    }

    @Test
    public void testViewStoryException() {
        Story story = new Story("story");
        library.addStory(story);
        library.addStory(new Story("story_1"));
        library.viewStory(new Story("dne"));
    }

    @Test
    public void testFindExistingStory() {
        Story story;
        Story story2;
        try {
            File f1 = new File("./data/story.txt");
            assertTrue(f1.delete());

            File f2 = new File("./data/story_1.txt");
            assertTrue(f2.delete());
        } catch (Exception e) {
            System.out.println("An error occurred in runBefore.");
        } finally {
            story = new Story("story");
            assertEquals("story", story.getName());
        }

        library.addStory(story);
        assertEquals(story.getName(), library.findStory("story").getName());
    }

    @Test
    public void testFindNonExistingStory() {
        assertNull(library.findStory("dne"));
    }

   /* @Test
    public void testSelectStory() {
        Scanner input = new Scanner(System.in);
        Story story = new Story("story");
        library.addStory(story);
        library.addStory(new Story("truck"));
        library.selectStory(input);
        input.close();
    }*/
}

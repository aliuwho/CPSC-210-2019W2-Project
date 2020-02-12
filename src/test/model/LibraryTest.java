package model;

import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void runBefore() {
        library = new Library();
    }

    @Test
    public void testAdd1Story() {
        try {
            library.addStory(new Story("story"));
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, library.size());
    }

    @Test
    public void testAddMultiStory() {
        try {
            library.addStory(new Story("story_1"));
            library.addStory(new Story("truck"));
            library.addStory(new Story("story"));
        } catch (Exception e) {
            fail();
        }
        assertEquals(3, library.size());
    }

    @Test
    public void testViewStory() {
        Story story = null;
        try {
            story = new Story("story");
            library.addStory(story);
            library.addStory(new Story("story_1"));
        } catch (Exception e) {
            fail();
        }
        try {
            library.viewStory(story);
        } catch (EmptyLibraryException | NotAStoryException e) {
            fail();
        }
    }

    @Test
    public void testViewStoryException() {
        Story story;
        try {
            story = new Story("story");
            library.addStory(story);
            library.addStory(new Story("story_1"));
        } catch (Exception e) {
            fail();
        }
        try {
            library.viewStory(new Story("dne"));
        } catch (NotAStoryException e) {
            System.out.println("Error caught!");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFindExistingStory() {
        Story story;
        try {
            File f1 = new File("./data/story.txt");
            assertTrue(f1.delete());

            File f2 = new File("./data/story_1.txt");
            assertTrue(f2.delete());
        } catch (Exception e) {
            System.out.println("An error occurred in runBefore.");
        } finally {
            try {
                story = new Story("story");
                assertEquals("story", story.getName());
                library.addStory(story);
                assertEquals(story.getName(), library.findStory("story").getName());

            } catch (Exception e) {
                fail();
            }

        }

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

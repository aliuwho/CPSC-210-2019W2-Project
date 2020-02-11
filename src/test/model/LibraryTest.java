package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(1,library.size());
    }

    @Test
    public void testAddMultiStory() {
        library.addStory(new Story("story"));
        library.addStory(new Story("story_1"));
        library.getStories();
        assertEquals(2,library.size());
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
}

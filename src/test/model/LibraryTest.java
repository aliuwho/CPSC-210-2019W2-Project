package model;

import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;
import model.exceptions.StoryNameDuplicateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Story story1;
    private Story story2;
    private Story story3;
    private Story story4;

    // NOTE: Due to file appending, test files must be deleted before running each test.
    @BeforeEach
    public void runBefore() {
        library = new Library();
        try {
            File f1 = new File("./data/TEST_FILE_FOR_STORY1.txt");
            assertTrue(f1.delete());

            File f2 = new File("./data/TEST_FILE_FOR_STORY2.txt");
            assertTrue(f2.delete());

            File f3 = new File("./data/TEST_FILE_FOR_STORY3.txt");
            assertTrue(f3.delete());

            File f4 = new File("./data/TEST_FILE_FOR_STORY4.txt");
            assertTrue(f4.delete());

            story1 = new Story("TEST_FILE_FOR_STORY1");
            story2 = new Story("TEST_FILE_FOR_STORY2");
            story3 = new Story("TEST_FILE_FOR_STORY3");
            story4 = new Story("TEST_FILE_FOR_STORY4");
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testSize() {
        assertEquals(0,library.size());

        library.addStory(story1);
        assertEquals(1,library.size());

        library.addStory(story2);
        assertEquals(2,library.size());
    }

    @Test
    public void testAdd1Story() {
        try {
            library.addStory(story1);
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, library.size());
    }

    @Test
    public void testAddMultiStory() {
        try {
            library.addStory(story2);
            library.addStory(story1);
            library.addStory(story3);
        } catch (Exception e) {
            fail();
        }
        assertEquals(3, library.size());
    }

    @Test
    public void testViewStory() {
        try {
            library.addStory(story4);
            System.out.println(library.getStoryText(story4));
        } catch (EmptyLibraryException | NotAStoryException e) {
            fail();
        }
    }

    @Test
    public void testViewStoryEmptyLibraryException() {
        Story story;
        try {
            story = new Story("VIEW_STORY_TEST");
            library.getStoryText(story);
        } catch (StoryNameDuplicateException | NotAStoryException | IOException e) {
            fail();
        } catch (EmptyLibraryException e) {
            System.out.println("No books to view in library!");
        } finally {
            File deleteViewTest = new File("./data/VIEW_STORY_TEST.txt");
            assertTrue(deleteViewTest.delete());
        }
    }

    @Test
    public void testViewStoryNotInLibraryException() {
        Story story;
        try {
            library.addStory(story1);
            library.addStory(story2);
            story = new Story("VIEW_STORY_TEST");
            library.getStoryText(story);
        } catch (StoryNameDuplicateException | IOException | EmptyLibraryException e) {
            fail();
        } catch (NotAStoryException e) {
            System.out.println("thats not in the library!");
        } finally {
            File deleteViewTest = new File("./data/VIEW_STORY_TEST.txt");
            assertTrue(deleteViewTest.delete());
        }
    }

    @Test
    public void testFindExistingStory() {
        try {
            library.addStory(story4);
            library.addStory(story2);
            library.addStory(story1);
            library.addStory(story3);
            assertEquals(story1.getName(), library.findStory("TEST_FILE_FOR_STORY1").getName());
        } catch (Exception e) {
            fail();
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

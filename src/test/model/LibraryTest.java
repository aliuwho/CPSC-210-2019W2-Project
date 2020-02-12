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
            if (!f1.createNewFile()) {
                assertTrue(f1.delete());
            }

            File f2 = new File("./data/TEST_FILE_FOR_STORY2.txt");
            if (!f2.createNewFile()) {
                assertTrue(f2.delete());
            }
            File f3 = new File("./data/TEST_FILE_FOR_STORY3.txt");
            if (!f3.createNewFile()) {
                assertTrue(f3.delete());
            }
            File f4 = new File("./data/TEST_FILE_FOR_STORY4.txt");
            if (!f4.createNewFile()) {
                assertTrue(f4.delete());
            }


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
        assertEquals(0, library.size());

        library.addStory(story1);
        assertEquals(1, library.size());

        library.addStory(story2);
        assertEquals(2, library.size());
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
        } catch (EmptyLibraryException | NotAStoryException | IOException e) {
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

    @Test
    public void testGetStoryTextNoException() {
        try {
            library.addStory(story1);
            story1.write("this is a test!");
            StringBuilder sb = new StringBuilder();
            sb.append("this is a test!\n");
            assertEquals(sb.substring(0), library.getStoryText(story1).substring(0));
        } catch (EmptyLibraryException | NotAStoryException | IOException e) {
            fail();
        }

    }

    @Test
    public void testGetStoryTextEmptyLibraryException() {
        try {
            story1.write("this is a test!");
            StringBuilder sb = new StringBuilder();
            sb.append("this is a test!\n");
            assertEquals(sb.substring(0), library.getStoryText(story1).substring(0));
            fail();
        } catch (EmptyLibraryException e) {
            System.out.println("caught library error :|!");
        } catch (NotAStoryException | IOException e) {
            fail();
        }

    }

    @Test
    public void testGetStoryTextNotAStoryException() {
        try {
            library.addStory(story1);
            story2.write("this is a test!");
            StringBuilder sb = new StringBuilder();
            sb.append("this is a test!\n");
            assertEquals(sb.substring(0), library.getStoryText(story2).substring(0));
            fail();
        } catch (NotAStoryException e) {
            System.out.println("caught story error :|!");
        } catch (EmptyLibraryException | IOException e) {
            fail();
        }

    }

    @Test
    public void testGetStoryTextIOException() {
        try {

            File testFile = new File("./data/TEST_FILE_READ_EXCEPTION.txt");
            if (!testFile.createNewFile()) {
                assertTrue(testFile.delete());
            }

            Story testStory = new Story("TEST_FILE_READ_EXCEPTION");
            testStory.write("this is a test!");
            assertTrue(testFile.setReadable(false));

            library.addStory(testStory);
            StringBuilder sb = new StringBuilder();
            sb.append("this is a test!\n");
            assertEquals(sb.substring(0), library.getStoryText(testStory).substring(0));
            fail();
        } catch (NotAStoryException | StoryNameDuplicateException | EmptyLibraryException e) {
            fail();
        } catch (IOException e) {
            System.out.println("io exception");
        }
    }

    @Test
    public void testGetStoriesNoException() {
        library.addStory(story1);
        library.addStory(story2);
        library.addStory(story3);
        library.addStory(story4);
        StringBuilder sb = new StringBuilder();
        sb.append("\t- ");
        sb.append(story1.getName());
        sb.append("\t- ");
        sb.append(story2.getName());
        sb.append("\t- ");
        sb.append(story3.getName());
        sb.append("\t- ");
        sb.append(story4.getName());

        try {
            assertEquals(sb.substring(0), library.getStories().substring(0));
        } catch (EmptyLibraryException e) {
            fail();
        }
    }

    @Test
    public void testGetStoriesEmptyLibException() {
        try {
            library.getStories().substring(0);
        } catch (EmptyLibraryException e) {
            System.out.println("library is empty");
        }
    }
}

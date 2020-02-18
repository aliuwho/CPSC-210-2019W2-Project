package model;

import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;
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
    private static final String STORY1_PATH = "./data/TEST_FILE_STORY1.txt";
    private static final String STORY2_PATH = "./data/TEST_FILE_STORY2.txt";
    private static final String STORY3_PATH = "./data/TEST_FILE_STORY3.txt";
    private static final String STORY4_PATH = "./data/TEST_FILE_STORY4.txt";

    // NOTE: Due to file appending, test files must be deleted before running each test.
    @BeforeEach
    public void runBefore() {
        try {
            /*File file = new File("./data/STARTER_emptylib_nonzeropts.json");
            FileWriter fw = new FileWriter(file);
            File file2 = new File("./data/STARTER_emptylib_nonzeropts.json");
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(file2);
            Object obj = jsonParser.parse(fileReader);
            JSONObject profile = (JSONObject) obj;
            fw.write(profile.toJSONString());
            fw.close();
            library = new Library(file);
*/
            library = new Library();
            File f1 = new File(STORY1_PATH);
            if (!f1.createNewFile()) {
                assertTrue(f1.delete());
            }

            File f2 = new File(STORY2_PATH);
            if (!f2.createNewFile()) {
                assertTrue(f2.delete());
            }
            File f3 = new File(STORY3_PATH);
            if (!f3.createNewFile()) {
                assertTrue(f3.delete());
            }
            File f4 = new File(STORY4_PATH);
            if (!f4.createNewFile()) {
                assertTrue(f4.delete());
            }


            story1 = new Story("STORY1", STORY1_PATH);
            story2 = new Story("STORY2", STORY2_PATH);
            story3 = new Story("STORY3", STORY3_PATH);
            story4 = new Story("STORY4", STORY4_PATH);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testSize() {
        assertEquals(0, library.getSize());

        library.addStory(story1);
        assertEquals(1, library.getSize());

        library.addStory(story2);
        assertEquals(2, library.getSize());
    }

    @Test
    public void testAdd1Story() {
        try {
            library.addStory(story1);
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, library.getSize());
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
        assertEquals(3, library.getSize());
    }

    @Test
    public void testViewStory() {
        try {
            library.addStory(story4);
            assertEquals(1, library.getSize());
            System.out.println(library.getStoryText(story4));
        } catch (EmptyLibraryException e) {
            fail();
        } catch (NotAStoryException e) {
            fail("not a story");
        } catch (IOException e) {
            fail("io");
        }
    }

    @Test
    public void testViewStoryEmptyLibraryException() {
        Story story;
        try {
            story = new Story("VIEW_STORY_TEST", "./data/VIEW_STORY_TEST.txt");
            StringBuilder temp = new StringBuilder();
            temp.append("\n");
            assertEquals(temp, library.getStoryText(story));
            fail();
        } catch (NotAStoryException | IOException e) {
            fail();
        } catch (EmptyLibraryException e) {
            System.out.println("No books to view in library!");
        } /*finally {
            File deleteViewTest = new File("./data/VIEW_STORY_TEST.txt");
//            assertTrue(deleteViewTest.delete());
        }*/
    }

    @Test
    public void testViewStoryNotInLibraryException() {
        Story story;
        try {
            library.addStory(story1);
            library.addStory(story2);
            story = new Story("VIEW_STORY_TEST", "./data/VIEW_STORY_TEST.txt");
            library.getStoryText(story);
            StringBuilder temp = new StringBuilder();
            temp.append("\n");
            assertEquals(temp, library.getStoryText(story));
        } catch (IOException | EmptyLibraryException e) {
            fail();
        } catch (NotAStoryException e) {
            System.out.println("that's not in the library!");
        } finally {
            File deleteViewTest = new File("./data/VIEW_STORY_TEST.txt");
            assertFalse(deleteViewTest.delete());
        }
    }

    @Test
    public void testFindExistingStory() {
        library.addStory(story4);
        library.addStory(story2);
        library.addStory(story1);
        library.addStory(story3);
        assertEquals(story1.getName(), library.findStory("STORY1").getName());
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
        } catch (EmptyLibraryException | IOException e) {
            fail();
        } catch (NotAStoryException e) {
            fail("not a story");
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

            Story testStory = new Story("TEST_FILE_READ_EXCEPTION", "./data/TEST_FILE_READ_EXCEPTION.txt");
            testStory.write("this is a test!");
            assertTrue(testFile.setReadable(false));

            library.addStory(testStory);
            StringBuilder sb = new StringBuilder();
            sb.append("this is a test!\n");
            assertEquals(sb.substring(0), library.getStoryText(testStory).substring(0));
            fail();
        } catch (NotAStoryException | EmptyLibraryException e) {
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

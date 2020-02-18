package model;

import model.exceptions.StoryNameDuplicateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {
    private static Story story1;
    private static Story story2;
    private static final String STORY1_PATH = "./data/TEST_FILE_STORY1.txt";
    private static final String STORY2_PATH = "./data/TEST_FILE_STORY2.txt";
    private static final String STORY3_PATH = "./data/TEST_FILE_STORY3.txt";
    private static final String STORY4_PATH = "./data/TEST_FILE_STORY4.txt";

    // NOTE: Due to file appending, test files must be deleted before running tests.
    @BeforeEach
    public void runBefore() {
        try {
            File f1 = new File(STORY1_PATH);
            if (!f1.createNewFile()) {
                assertTrue(f1.delete());
            }

            File f2 = new File(STORY2_PATH);
            if (!f2.createNewFile()) {
                assertTrue(f2.delete());
            }

        } catch (Exception e) {
            fail();
        } finally {
            try {
                story1 = new Story("STORY1", STORY1_PATH);
                assertEquals("STORY1", story1.getName());
                story2 = new Story("STORY2", STORY2_PATH);
                assertEquals("STORY2", story2.getName());
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    public void testConstructorNoException() {
        File file = new File("./data/TEST_FILE_NO_EXCEPTION.txt");
        /*try {
            if (!file.createNewFile()) {
                assertTrue(file.delete());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Story testStory = new Story("TEST_FILE_NO_EXCEPTION", "./data/TEST_FILE_NO_EXCEPTION.txt");
        assertEquals("TEST_FILE_NO_EXCEPTION",testStory.getName());
        assertEquals("./data/TEST_FILE_NO_EXCEPTION.txt",testStory.getPath());
    }

//    @Test
//    public void testConstructorDuplicateName() {
//        try {
//            Story story3 = new Story("TEST_FILE_STORY2", STORY2_PATH);
//            fail();
//        } catch (StoryNameDuplicateException e) {
//            System.out.println("duplicate file error caught :^)");
//        } catch (IOException e) {
//            fail();
//        }
//    }

//    @Test
//    public void testConstructorException() {
//        try {
//            Story s3 = new Story("testing", "./story/TEST_FILE_FOR_STORY2.txt");
//            fail();
//        } catch (IOException e) {
//            System.out.println("ioerror! caught!");
//        } catch (StoryNameDuplicateException e) {
//            fail();
//        }
//    }

    @Test
    public void testWriteException() {
        Story s3;
        try {
            s3 = new Story("TEST_FILE_WRITE_EXCEPTION", "./data/TEST_FILE_WRITE_EXCEPTION.txt");
            FileInputStream in = new FileInputStream(s3.getPath());
            java.nio.channels.FileLock lock = in.getChannel().lock();
            s3.write("heeho");
        } catch (Exception e) {
            System.out.println("error!!!");
        }
    }

    @Test
    public void testWriteOneLine() {
        try {
            story1.write("your substance is filth");
        } catch (IOException e) {
            fail();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(story1.getPath()));
            String line;
            StringBuilder fullText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                fullText.append(line);
            }
            assertEquals("your substance is filth", fullText.toString());
        } catch (Exception e) {
            System.out.println("An error occurred!");
            fail();
        }
    }

    @Test
    public void testWriteMultiLine() {
        try {
            story2.write("who are they to say what the truth is anyway?");
            story2.write("play the game, but don't believe in it--that much you owe yourself.");

        } catch (Exception e) {
            fail();
        }
        //story2.write("i AM an invisible man.");
        try {
            BufferedReader br = new BufferedReader(new FileReader(story2.getPath()));
            String line;
            StringBuilder fullText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                fullText.append(line).append("\n");
            }
            assertEquals("who are they to say what the truth is anyway?" +
                    "\nplay the game, but don't believe in it--that much you owe yourself.\n", fullText.toString());
        } catch (Exception e) {
            System.out.println("An error occurred!");
            fail();
        }
    }

}

package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {
    private static Story story1;
    private static Story story2;
    //private Story story2;

    // NOTE: Due to file appending, you MUST NOT HAVE an existing story.txt or story1.txt before running tests
    //       To solve this issue, BeforeAll deletes any existing story.txt and story1.txt files
    @BeforeAll
    static void runBefore() {
        try {
            File f1 = new File("./data/story.txt");
            assertTrue(f1.delete());

            File f2 = new File("./data/story_1.txt");
            assertTrue(f2.delete());
        } catch (Exception e) {
            System.out.println("An error occurred in runBefore.");
        } finally {
            story1 = new Story("story");
            assertEquals("story", story1.getName());
            story2 = new Story("story");
        }
    }

    @Test
    public void testConstructorDuplicateName() {
        assertEquals("story_1", story2.getName());
    }

    @Test
    public void testWriteOneLine() {
        story1.write("your substance is filth");
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/story.txt"));
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
        story2.write("who are they to say what the truth is anyway?");
        story2.write("play the game, but don't believe in it--that much you owe yourself.");
        //story2.write("i AM an invisible man.");
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/story_1.txt"));
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

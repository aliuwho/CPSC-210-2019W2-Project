package model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveableTest {
    private File starter1;
    private File file1;

    private File starter2;
    private File file2;

    @BeforeEach
    public void runBefore() {
        try {
            file1 = new File("./data/TEST_elib_nzpts.json");
            starter1 = new File("./data/starters/STARTER_emptylib_nonzeropts.json");
            FileWriter fw = new FileWriter(file1);

            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(starter1);
            Object obj = jsonParser.parse(fileReader);
            JSONObject profile = (JSONObject) obj;
            fw.write(profile.toJSONString());
            fw.close();
        } catch (ParseException | IOException e) {
            System.out.println("File1/Starter1 error");
            e.printStackTrace();
        }

        try {
            file2 = new File("./data/TEST_nelib_nzpts.json");
            starter2 = new File("./data/starters/STARTER_nonemptylib_nonzeropts.json");
            FileWriter fw = new FileWriter(file2);

            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(starter2);
            Object obj = jsonParser.parse(fileReader);
            JSONObject profile = (JSONObject) obj;
            fw.write(profile.toJSONString());
            fw.close();
        } catch (ParseException | IOException e) {
            System.out.println("File2/Starter2 error");
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorExistingData() {
        try {
            Saveable s = new Saveable(file1.getPath());
            assertEquals("TEST_FILE", s.getName());
            assertEquals(69, s.getPoints());
            assertEquals(0, s.getLibrary().getSize());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet");
        }

        try {
            Saveable s = new Saveable(file2.getPath());
            assertEquals("TEST_FILE", s.getName());
            assertEquals(69, s.getPoints());
            assertEquals(2, s.getLibrary().getSize());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet2");
        }

    }

    @Test
    public void testConstructorNoData() {
        try {
            Saveable s = new Saveable(file1.getPath());
            assertEquals("TEST_FILE", s.getName());
            assertEquals(69, s.getPoints());
            assertEquals(0, s.getLibrary().getSize());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet");
        }

    }
}

package model;

import model.animals.Animal;
import model.animals.Bird;
import model.animals.Horse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Saveable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SaveableTest {
    private File file1;
    private File file2;
    private File file3;

    @BeforeEach
    public void runBefore() {
        try {
            file1 = new File("./data/TEST_elib_nzpts.json");
            File starter = new File("./data/starters/points_full.json");
            FileWriter fw = new FileWriter(file1);

            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(starter);
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
            File starter = new File("./data/starters/library_full_points_full.json");
            FileWriter fw = new FileWriter(file2);

            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(starter);
            Object obj = jsonParser.parse(fileReader);
            JSONObject profile = (JSONObject) obj;
            fw.write(profile.toJSONString());
            fw.close();
        } catch (ParseException | IOException e) {
            System.out.println("File2/Starter2 error");
            e.printStackTrace();
        }

        try {
            file3 = new File("./data/TEST_WNE.json");
            File starter = new File("./data/starters/wne.json");
            FileWriter fw = new FileWriter(file3);

            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(starter);
            Object obj = jsonParser.parse(fileReader);
            JSONObject profile = (JSONObject) obj;
            fw.write(profile.toJSONString());
            fw.close();
        } catch (ParseException | IOException e) {
            System.out.println("File3/Starter3 error");
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
            assertEquals(0, s.getPets().size());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet");
        }

        try {
            Saveable s = new Saveable(file2.getPath());
            assertEquals("TEST_FILE", s.getName());
            assertEquals(69, s.getPoints());
            assertEquals(2, s.getLibrary().getSize());
            assertEquals(0, s.getPets().size());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet2");
        }

    }

    @Test
    public void testConstructorNoData() {
        Saveable s = new Saveable("./data/NO_DATA.json", "NO_DATA", LocalDateTime.now());
        assertEquals("NO_DATA", s.getName());
        assertEquals(0, s.getPoints());
        assertEquals(0, s.getLibrary().getSize());

    }

    @Test
    public void testAddPoints() {
        try {
            Saveable s = new Saveable(file2.getPath());
            assertEquals(69, s.getPoints());
            s.addPoints(4200);
            assertEquals(4269, s.getPoints());
        } catch (IOException | ParseException e) {
            fail("io/parse");
            e.printStackTrace();
        }

    }

    @Test
    public void testWriteNoException() {
        try {
            Saveable s = new Saveable("./data/TEST_WNE.json", "TEST_WRITE_NO_E", LocalDateTime.now());
            Bird jarod = new Bird("jarod");
            Horse suzie = new Horse("suzie");
            ArrayList<Animal> pets = s.getPets();
            pets.add(jarod);
            pets.add(suzie);
            s.write();
            File temp = new File(s.getFile().getPath());
            assertNotEquals(0, temp.length());
            assertEquals(2, s.getPets().size());
        } catch (IOException e) {
            fail("io");
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteException() {
        try {
            Saveable s = new Saveable("./data/TEST_WE.json", "TEST_WRITE_EXCEPTION", LocalDateTime.now());
            File temp = new File(s.getFile().getPath());
            assertTrue(temp.setWritable(false));
            s.write();
            fail();
        } catch (IOException e) {
            System.out.println("io error!!!");
        }
    }
}

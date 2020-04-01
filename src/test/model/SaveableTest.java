package model;

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
import java.time.format.DateTimeFormatter;
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
            assertEquals("TEST_FILE", s.getUsername());
            assertEquals(69, s.getPoints());
            assertEquals(0, s.getLibrary().getSize());
            assertEquals(0, s.getPets().size());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet");
        }

        try {
            Saveable s = new Saveable(file2.getPath());
            assertEquals("TEST_FILE", s.getUsername());
            assertEquals(69, s.getPoints());
            assertEquals(2, s.getLibrary().getSize());
            assertEquals(0, s.getPets().size());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet2");
        }

        try {
            Saveable s = new Saveable(file3.getPath());
            assertEquals("TEST_FILE", s.getUsername());
            assertEquals(0, s.getPoints());
            assertEquals(0, s.getLibrary().getSize());
            assertEquals(0, s.getPets().size());
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
            fail("yeet3");
        }

        try {
            Saveable s = new Saveable("./data/starters/pets_full.json");
            assertEquals("TEST_FILE", s.getUsername());
            assertEquals(50, s.getPoints());
            assertEquals(0, s.getLibrary().getSize());
            assertEquals(5, s.getPets().size());
        } catch (ParseException | IOException e) {
            fail("yeet4");
        }

    }

    @Test
    public void testConstructorNoData() {
        Saveable s = new Saveable("./data/NO_DATA.json", "NO_DATA", LocalDateTime.now());

        DateTimeFormatter dateTimeFormatter = Saveable.DATE_TIME_FORMATTER;
        LocalDateTime localDateTime = LocalDateTime.now();

        assertEquals("NO_DATA", s.getUsername());
        assertEquals(0, s.getPoints());
        assertEquals(0, s.getLibrary().getSize());
        assertEquals(localDateTime.format(dateTimeFormatter), s.getStart());

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

            Pet jarod = new Pet("jarod", "bird");
            Pet suzie = new Pet("suzie", "horse");
            Pet anushka = new Pet("Anushka", "lizard");
            ArrayList<Pet> pets = s.getPets();
            pets.add(jarod);
            pets.add(suzie);
            pets.add(anushka);

            Library lib = s.getLibrary();
            Story story = new Story("truck", "./data/truck.txt");
            lib.addStory(story);

            s.write();

            File temp = new File(s.getFile().getPath());
            assertNotEquals(0, temp.length());
            assertEquals(1, s.getLibrary().getSize());
            assertEquals(3, s.getPets().size());
        } catch (IOException e) {
            fail("io");
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteException() {
        try {
            Saveable s = new Saveable("./data/TEST_WNE.json", "TEST_WRITE_EXCEPTION", LocalDateTime.now());
            File temp = new File(s.getFile().getPath());
            assertTrue(temp.setWritable(false));
            s.write();
            fail();
        } catch (IOException e) {
            System.out.println("io error!!!");
        } finally {
            File temp = new File("./data/TEST_WNE.json");
            assertTrue(temp.setWritable(true));
        }
    }

    @Test
    public void testFindPetTrue() {
        try {
            Saveable s = new Saveable(file1.getPath());
            Pet horse = new Pet("yee haw", "horse");
            s.getPets().add(new Pet("sully", "dog"));
            s.getPets().add(new Pet("beepo", "cat"));
            s.getPets().add(horse);
            assertEquals(horse, s.findPet("yee haw"));
        } catch (IOException | ParseException e) {
            fail();
        }
    }

    @Test
    public void testFindPetNope() {
        try {
            Saveable s = new Saveable(file1.getPath());
            Pet horse = new Pet("yee haw", "horse");
            s.getPets().add(new Pet("sully", "dog"));
            s.getPets().add(new Pet("beepo", "cat"));
            s.getPets().add(horse);
            assertNull(s.findPet("not existing"));
        } catch (IOException | ParseException e) {
            fail();
        }
    }
}

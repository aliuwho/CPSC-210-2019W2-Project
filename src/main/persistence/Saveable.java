package persistence;

import model.Library;
import model.Story;
import model.Pet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * represents data to be saved from the console
 */
@SuppressWarnings("unchecked")
public class Saveable {

    private long points;
    private String username;
    private Library library;
    private ArrayList<Pet> pets;
    private File file;
    private String start;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("H:m',' MMMM d',' yyyy");


    // EFFECTS: creates a new user profile
    public Saveable(String path, String username, LocalDateTime now) {
        this.file = new File(path);
        this.library = new Library();
        this.username = username;
        this.points = 0;
        this.pets = new ArrayList<>();
        this.start = now.format(DATE_TIME_FORMATTER);
    }

    // EFFECTS: creates a Saveable object with data from file
    public Saveable(String path) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = jsonParser.parse(fileReader);
        JSONObject temp = (JSONObject) obj;

        this.username = (String) temp.get("name");
        this.points = (long) temp.get("points");
        this.start = (String) temp.get("start");
        JSONArray lib = (JSONArray) temp.get("library");
        this.library = toLibrary(lib);
        JSONArray petList = (JSONArray) temp.get("pets");
        this.pets = toPets(petList);
        this.file = new File(path);
    }

    // EFFECTS: returns file
    public File getFile() {
        return file;
    }


    // EFFECTS: returns library
    public Library getLibrary() {
        return library;
    }

    // EFFECTS: returns name
    public String getUsername() {
        return username;
    }

    // EFFECTS: returns pets
    public ArrayList<Pet> getPets() {
        return pets;
    }

    // EFFECTS: returns points
    public long getPoints() {
        return points;
    }

    // EFFECTS: returns start time
    public String getStart() {
        return start;
    }

    // EFFECTS: if pet with name is in pets, returns the pet;
    //          otherwise, returns null
    public Pet findPet(String petName) {
        for (Pet p : pets) {
            if (p.getName().equals(petName)) {
                return p;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to points
    public void addPoints(int amount) {
        this.points += amount;
    }

    // EFFECTS: writes JSON file using Saveable data
    public void write() throws IOException {
        JSONObject profile = new JSONObject();
        profile.put("name", username);
        profile.put("points", points);
        profile.put("start", start);
        JSONArray libraryObj = libToJsonArray(library);
        profile.put("library", libraryObj);
        JSONArray petsObj = petsToJsonArray(pets);
        profile.put("pets", petsObj);
        FileWriter fw = new FileWriter(file.getPath(), false);
        fw.write(profile.toJSONString());
        fw.close();
    }

    // EFFECTS: transforms pets into a JSONArray
    private JSONArray petsToJsonArray(ArrayList<Pet> pets) {
        JSONArray array = new JSONArray();
        for (Pet pet : pets) {
            array.add(petToJsonObj(pet));
        }
        return array;
    }

    // EFFECTS: turns a pet into a JSONObject
    private JSONObject petToJsonObj(Pet pet) {
        JSONObject obj = new JSONObject();
        obj.put("name", pet.getName());
        obj.put("species", pet.getSpecies());
        obj.put("tired", pet.isTired());
        obj.put("hungry", pet.isHungry());
        return obj;
    }

    // EFFECTS: transforms Library into a JSONArray
    private JSONArray libToJsonArray(Library library) {
        JSONArray array = new JSONArray();
        for (Story s : library.getStoryList()) {
            array.add(storyToJsonObj(s));
        }
        return array;

    }

    // EFFECTS: transforms Story into a JSON Object
    private JSONObject storyToJsonObj(Story s) {
        JSONObject o = new JSONObject();
        o.put("name", s.getName());
        //noinspection unchecked
        o.put("path", s.getPath());
        return o;
    }

    // EFFECTS: transforms a parsed JSONArray into a library
    private Library toLibrary(JSONArray lib) {
        Library temp = new Library();
        for (Object value : lib) {
            JSONObject o = (JSONObject) value;
            Story s = toStory(o);
            temp.addStory(s);
        }
        return temp;
    }

    // EFFECTS: transforms parsed JSONObject into a Story
    private Story toStory(JSONObject o) {
        return new Story((String) o.get("name"), (String) o.get("path"));
    }


    // EFFECTS: turns JSONArray into pets
    private ArrayList<Pet> toPets(JSONArray petList) {
        ArrayList<Pet> temp = new ArrayList<>();
        for (Object value : petList) {
            JSONObject o = (JSONObject) value;
            Pet pet = toPet(o);
            temp.add(pet);
        }
        return temp;
    }

    // EFFECTS: turns JSONObject into an Animal
    private Pet toPet(JSONObject o) {
        String species = (String) o.get("species");
        String name = (String) o.get("name");
        boolean hungry = (boolean) o.get("hungry");
        boolean tired = (boolean) o.get("tired");
        if ("bird".equals(species)) {
            return new Pet(name, "bird", hungry, tired);
        } else if ("cat".equals(species)) {
            return new Pet(name, "cat", hungry, tired);
        } else if ("dog".equals(species)) {
            return new Pet(name, "dog", hungry, tired);
        } else if ("horse".equals(species)) {
            return new Pet(name, "horse", hungry, tired);
        } else {
            return new Pet(name, "lizard", hungry, tired);
        }
//            default:
//                System.out.println("Unexpected value: " + species);
//                return null;
//    }
    }
}

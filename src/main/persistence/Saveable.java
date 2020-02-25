package persistence;

import model.Library;
import model.Story;
import model.animals.*;
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

@SuppressWarnings("unchecked")
public class Saveable {

    private long points;
    private String name;
    private Library library;
    private ArrayList<Animal> pets;
    private File file;
    private String start;

    // EFFECTS: creates a new user profile
    public Saveable(String path, String name, LocalDateTime now) {
        this.file = new File(path);
        this.library = new Library();
        this.name = name;
        this.points = 0;
        this.pets = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m',' MMMM d',' yyyy");
        this.start = now.format(dtf);
    }

    // EFFECTS: creates a Saveable object with data from file
    public Saveable(String path) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = jsonParser.parse(fileReader);
        JSONObject temp = (JSONObject) obj;

        this.name = (String) temp.get("name");
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
    public String getName() {
        return name;
    }

    // EFFECTS: returns pets
    public ArrayList<Animal> getPets() {
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

    // MODIFIES: this
    // EFFECTS: adds amount to points
    public void addPoints(int amount) {
        this.points += amount;
    }

    // EFFECTS: writes JSON file using Saveable data
    public void write() throws IOException {
        JSONObject profile = new JSONObject();
        profile.put("name", name);
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
    private JSONArray petsToJsonArray(ArrayList<Animal> pets) {
        JSONArray array = new JSONArray();
        for (Animal pet : pets) {
            array.add(petToJsonObj(pet));
        }
        return array;
    }

    // EFFECTS: turns a pet into a JSONObject
    private JSONObject petToJsonObj(Animal pet) {
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
    private ArrayList<Animal> toPets(JSONArray petList) {
        ArrayList<Animal> temp = new ArrayList<>();
        for (Object value : petList) {
            JSONObject o = (JSONObject) value;
            Animal animal = toPet(o);
            temp.add(animal);
        }
        return temp;
    }

    // EFFECTS: turns JSONObject into an Animal
    private Animal toPet(JSONObject o) {
        String species = (String) o.get("species");
        String name = (String) o.get("name");
        boolean hungry = (boolean) o.get("hungry");
        boolean tired = (boolean) o.get("tired");
        switch (species) {
            case "bird":
                return new Bird(name, hungry, tired);
            case "cat":
                return new Cat(name, hungry, tired);
            case "dog":
                return new Dog(name, hungry, tired);
            case "horse":
                return new Horse(name, hungry, tired);
            case "lizard":
                return new Lizard(name, hungry, tired);
            default:
                System.out.println("Unexpected value: " + species);
                return null;

        }
    }
}

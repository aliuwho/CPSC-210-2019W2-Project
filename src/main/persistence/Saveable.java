package persistence;

import model.Library;
import model.Story;
import model.animals.Animal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saveable {

    private long points;
    private String name;
    private Library library;
    //    private ArrayList<Animal> pets;
    private Animal pet;
    private File file;
    //  JSONObject profile;

    // EFFECTS: creates a new user profile
    public Saveable(String path, String name) {
        this.file = new File(path);
        this.library = new Library();
        this.name = name;
        this.points = 0;
        this.pet = null;
    }

    // TODO: add multiple pets haha....
    // EFFECTS: creates a Saveable object with data from file
    public Saveable(String path) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        Object obj = jsonParser.parse(fileReader);
        JSONObject temp = (JSONObject) obj;

        this.name = (String) temp.get("name");
        this.points = (long) temp.get("points");
        JSONArray lib = (JSONArray) temp.get("library");
        this.library = toLibrary(lib);
        this.pet = null;
        this.file = new File(path);
    }

    public String getName() {
        return name;
    }

// --Commented out by Inspection START (2/18/20, 10:04 AM):
//    //TO-DO: add ability to change username?
//    public void setName(String name) {
//        this.name = name;
//    }
// --Commented out by Inspection STOP (2/18/20, 10:04 AM)

    public long getPoints() {
        return points;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to points
    public void addPoints(int amount) {
        this.points += amount;
    }

    public Library getLibrary() {
        return library;
    }

//    public Animal getPet() {
//        return pet;
//    }
//
//    public File getFile() {
//        return file;
//    }

    // EFFECTS: writes JSON file using Saveable data
    public void write() throws IOException {
        JSONObject profile = new JSONObject();
        profile.put("name", name);
        profile.put("points", points);
        JSONArray libraryObj = libToJsonArray(library);
        profile.put("library", libraryObj);
        FileWriter fw = new FileWriter(file.getPath(), false);
        fw.write(profile.toJSONString());
        fw.close();
    }

    // EFFECTS: transforms Library into a JSONArray
    public JSONArray libToJsonArray(Library library) {
        JSONArray array = new JSONArray();
//        ArrayList<JSONObject> jarr = new ArrayList<>();
        for (Story s : library.getStoryList()) {
            array.add(storyToJsonObj(s));
        }
        return array;

    }

    // EFFECTS: transforms Story into a JSON Object
    public JSONObject storyToJsonObj(Story s) {
        JSONObject o = new JSONObject();
        o.put("name", s.getName());
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
//        for (JSONObject o : lib) {
//            Story s = toStory(o);
//            temp.addStory(s);
//        }
        return temp;
    }

    // EFFECTS: transforms parsed JSONObject into a Story
    private Story toStory(JSONObject o) {
        return new Story((String) o.get("name"), (String) o.get("path"));
    }
}

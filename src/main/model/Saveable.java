package model;

import model.animals.Animal;
import model.exceptions.StoryNameDuplicateException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saveable {

    private int points;
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
//        this.points = (long) temp.get("points");
        this.points = 0;
        JSONArray lib = (JSONArray) temp.get("library");
        this.library = toLibrary(lib);
        this.pet = null;
        this.file = new File(path);
    }

    public String getName() {
        return name;
    }

    //TODO: add ability to change username?
    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Library getLibrary() {
        return library;
    }

    public Animal getPet() {
        return pet;
    }

    public File getFile() {
        return file;
    }

    // EFFECTS: writes JSON file using Saveable data
    public void write() throws IOException {
        JSONObject profile = new JSONObject();
        profile.put("name", name);
        profile.put("points", (long) points);
        JSONArray libraryObj = libToJsonArray(library);
        profile.put("library", libraryObj);
        FileWriter fw = new FileWriter(file.getPath(), false);
        fw.write(profile.toJSONString());
        fw.close();
    }

    // EFFECTS: transforms Library into a JSONArray
    public JSONArray libToJsonArray(Library library) {
        JSONArray jarr = new JSONArray();
        for (Story s : library.getStoryList()) {
            jarr.add(storyToJsonObj(s));
        }
        return jarr;

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
        for (Object o : lib) {
            Story s = toStory((JSONObject) o);
            temp.addStory(s);
        }
        return temp;
    }

    // EFFECTS: transforms parsed JSONObject into a Story
    private Story toStory(JSONObject o) {
        try {
            Story s = new Story((String) o.get("name"), (String) o.get("path"));
            return s;
        } catch (StoryNameDuplicateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("io");
            e.printStackTrace();
        }
        return null;
    }
}

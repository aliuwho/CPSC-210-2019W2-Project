package model;

import model.exceptions.StoryNameDuplicateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Story {
    // delete or rename this class!
    private String name;
    private String path;
//    private JSONObject storyObj;
//    private JSONArray text;
//    private File storyFile;

    // EFFECTS: if file with name already exists, throws StoryNameDuplicateException;
    //          otherwise, if file cannot be read, throws IOException;
    //          otherwise, creates a blank story called name
    public Story(String name, String path) throws StoryNameDuplicateException, IOException {
//        storyObj = new JSONObject();
//        text = new JSONArray();
//        this.storyFile = storyFile;
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader(storyFile);
//        Object obj = jsonParser.parse(fileReader);
//        JSONArray profile = (JSONArray) obj;
//        JSONArray library = (JSONArray) profile.get(Profile.keys.get("LIBRARY"));
//        for (Object temp : library) {
//            JSONObject story = (JSONObject) temp;
//            if (story.get("name").equals(name)) {
//                throw new StoryNameDuplicateException();
//            }
//        }
//
//        this.name = name;
//        storyObj.put("StoryName", name);

        this.path = path;
        File f = new File(path);
        this.name = name;
       /* if (f.createNewFile()) {
            this.name = name;
        } else if (!f.createNewFile()) {
            this.name = "error";
            this.path = "";
            throw new StoryNameDuplicateException();
            // TODO: remove storyname duplicate from story constructor
        }*/
    }

    // EFFECTS: adds substance to a new line of your story!
    public void write(String substance) throws IOException {

        FileWriter f = new FileWriter(path, true);
        f.write(substance + "\n");
        f.close();
//        text = new JSONArray();
//        text.add(substance);
    }

    // EFFECTS: returns story name
    public String getName() {
        return name;
    }

    // EFFECTS: returns path to story
    public String getPath() {
        return path;
    }

    /*// EFFECTS: returns story text
    public JSONArray getStoryLines() {
        return
    }*/
}

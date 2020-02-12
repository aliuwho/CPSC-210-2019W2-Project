package model;

import model.exceptions.StoryNameDuplicateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Story {
    // delete or rename this class!
    private String name;
    public static final String WRITE_TO = "./data/";
    private String path;

    // EFFECTS: creates a blank story called name
    public Story(String name) throws StoryNameDuplicateException, IOException {
        path = WRITE_TO + name + ".txt";
        File f = new File(path);
        if (f.createNewFile()) {
            this.name = name;
        } else if (!f.createNewFile()) {
            this.name = "error";
            this.path = "";
            throw new StoryNameDuplicateException();
        }
    }

    // EFFECTS: adds substance to a new line of your story!
    public void write(String substance) throws IOException {
        FileWriter f = new FileWriter(path,true);
        f.write(substance + "\n");
        f.close();

    }

    // EFFECTS: returns story name
    public String getName() {
        return name;
    }

    // EFFECTS: returns path to story
    public String getPath() {
        return path;
    }
}

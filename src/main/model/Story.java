package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * represents a collection of words; a story
 */
public class Story {
    // delete or rename this class!
    private final String name;
    private final String path;

    // EFFECTS: if file with name already exists, throws StoryNameDuplicateException;
    //          otherwise, if file cannot be read, throws IOException;
    //          otherwise, creates a blank story called name
    public Story(String name, String path) {
        this.path = path;
//        File f = new File(path);
        this.name = name;
    }

    // EFFECTS: adds substance to a new line of your story!
    public void write(String substance) throws IOException {
        FileWriter f = new FileWriter(path, true);
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

    // EFFECTS: returns story text
    public String getStoryText() throws IOException {
        StringBuilder fullText = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            fullText.append(line).append("\n");
        }
        return fullText.toString();
    }

}

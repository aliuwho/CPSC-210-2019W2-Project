package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Story {
    // delete or rename this class!
    private String name;
    public static final String WRITE_TO = "./data/";
    private String path;

    // EFFECTS: creates a blank story called name
    public Story(String name) {
        try {
            path = WRITE_TO + name + ".txt";
            File f = new File(path);
            if (f.createNewFile()) {
                System.out.println("Your story will be saved as " + name + "!");
                this.name = name;
            } else {
                path = WRITE_TO + name + "_1.txt";
                this.name = name + "_1";
                System.out.println("That name already exists! Your story will be saved as " + this.name + " instead!");

            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    // EFFECTS: adds substance to a new line of your story!
    public void write(String substance) {
        try {
            FileWriter f = new FileWriter(path,true);
            f.write(substance + "\n");
            f.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

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

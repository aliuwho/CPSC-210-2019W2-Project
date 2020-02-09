package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Story {
    // delete or rename this class!
    private String name;
    public static final String WRITE_TO = "./data/";

    // EFFECTS: creates a blank story called name
    public Story(String name) {
        try {
            File f = new File(WRITE_TO + name + ".txt");
            if (f.createNewFile()) {
                System.out.println("Your story will be saved as " + name + "!");
                this.name = name;
            } else {

                System.out.println("That name already exists! Your story will be saved as " + name + "_1 instead!");
                this.name = name + "_1";
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // EFFECTS: adds substance to a new line of your story!
    public void write(String substance) {
        try {
            FileWriter f = new FileWriter(WRITE_TO + name + ".txt",true);
            f.write(substance + "\n");
            f.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

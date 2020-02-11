package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Library {
    private ArrayList<Story> stories;

    // EFFECTS: creates an empty library
    public Library() {
        stories = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS; adds story to library
    public void addStory(Story s) {
        stories.add(s);
    }

    // EFFECTS: lists stories in library
    public void getStories() {
        for (Story s : stories) {
            System.out.println(s.getName());
        }
    }

    // EFFECTS: displays story
    public void viewStory(Story story) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(story.getPath()));
            String line;
            StringBuilder fullText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                fullText.append(line).append("\n");
            }
            System.out.println(fullText);
        } catch (IOException e) {
            System.out.println("Could not read story.");
        }
    }

    // EFFECTS: finds story in library witch matching title; returns null if dne
    public Story findStory(String title) {
        for (Story story : stories) {
            if (story.getName().equals(title)) {
                return story;
            }
        }
        return null;
    }

    // EFFECTS: returns number of stories in library
    public int size() {
        return stories.size();
    }
}

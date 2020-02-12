package model;

import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;

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

    // EFFECTS: if no stories in library, throws EmptyLibraryException;
    //          else, lists stories in library
    public StringBuilder getStories() throws EmptyLibraryException {
        StringBuilder storyList = new StringBuilder();
        if (stories.size() == 0) {
            throw new EmptyLibraryException();
        } else {
            for (Story s : stories) {
                storyList.append("\t- ").append(s.getName());
            }
        }
        return storyList;
    }

    // EFFECTS: if library is empty, throw EmptyLibraryException;
    //          if library is not empty but Story is not in library, throw NotAStoryException;
    //          else returns the text;
    public StringBuilder getStoryText(Story story) throws EmptyLibraryException, NotAStoryException {
        StringBuilder fullText = new StringBuilder();
        if (size() == 0) {
            throw new EmptyLibraryException();
        } else if (stories.contains(story)) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(story.getPath()));
                String line;
                while ((line = br.readLine()) != null) {
                    fullText.append(line).append("\n");
                }
                return fullText;
            } catch (IOException e) {
                System.out.println("Could not read story.");
            }
        } else {
            throw new NotAStoryException();
        }
        return fullText;
    }

    // EFFECTS: finds story in library witch matching title; returns null if story is not in library
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

package model;

import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Library {
    private ArrayList<Story> stories;
    //private File libraryFile;
    // private JSONArray libraryObj = new JSONArray();

    // EFFECTS: creates a library from JSON file
    public Library() {
        stories = new ArrayList<>();
        //this.libraryFile = libraryFile;
/*

        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(libraryFile);
        Object obj = jsonParser.parse(fileReader);
        JSONObject profile = (JSONObject) obj;
        libraryObj = (JSONArray) profile.get("library");
*/

    }

    // MODIFIES: this
    // EFFECTS; adds story to library
    public void addStory(Story s) {
/*
        try {
            FileWriter file = new FileWriter(libraryFile, true);
            file.write(libraryObj.toJSONString());
            JSONObject story = new JSONObject();
            story.put("name", s.getName());
            story.put("path", s.getPath());
            libraryObj.add(story);
            file.close();

        } catch (IOException e) {
            System.out.println("IO Exception occurred");
        }*/
        stories.add(s);
    }

    // EFFECTS: if no stories in library, throws EmptyLibraryException;
    //          else, lists stories in library
    public StringBuilder getStories() throws EmptyLibraryException {
        StringBuilder storyList = new StringBuilder();
        if (isEmpty()) {
            throw new EmptyLibraryException();
        } else {
            /*for (Object s : libraryObj) {
                JSONObject story = (JSONObject) s;
                String storyName = (String) story.get("name");
                storyList.append("\t- ").append(storyName);
            }*/
            for (Story s : stories) {
                storyList.append("\t- ").append(s.getName());
            }
        }
        return storyList;
    }

    // EFFECTS: if library is empty, throw EmptyLibraryException;
    //          if library is not empty but Story is not in library, throw NotAStoryException;
    //          else returns the text;
    public StringBuilder getStoryText(Story story) throws EmptyLibraryException, NotAStoryException, IOException {
        StringBuilder fullText = new StringBuilder();
        if (size() == 0) {
            throw new EmptyLibraryException();
        } else if (findStory(story.getName()) != null) {

            BufferedReader br = new BufferedReader(new FileReader(story.getPath()));
            String line;
            while ((line = br.readLine()) != null) {
                fullText.append(line).append("\n");
            }
            return fullText;
        } else {
            throw new NotAStoryException();
        }
    }

    // EFFECTS: finds story in library witch matching title; returns null otherwise
    public Story findStory(String title) {
        for (Story story : stories) {
            if (story.getName().equals(title)) {
                return story;
            }
        }
        return null;
//        for (Object o : libraryObj) {
//            JSONObject story = (JSONObject) o;
//            if (story.get("name").equals(title)) {
//                try {
//                    return new Story((String) story.get("name"), (String) story.get("path"));
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//        }
//        return null;
    }

    // EFFECTS: returns number of stories in library
    public int size() {
        return stories.size();
    }

    // EFFECTS: returns true if there are no stories in library and false otherwise
    public boolean isEmpty() {
        return stories.size() == 0;
    }

    // EFFECTS: returns a list of Stories
    public ArrayList<Story> getStoryList() {
        return stories;
    }
}

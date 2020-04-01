package model;

import model.exceptions.EmptyLibraryException;
import model.exceptions.NotAStoryException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * represents a library with stories
 */
public class Library {
    private final ArrayList<Story> stories;
    //private File libraryFile;
    // private JSONArray libraryObj = new JSONArray();

    // EFFECTS: creates an empty library
    public Library() {
        stories = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS; adds story to library in alphabetical order if no story already has the same name;
    //          otherwise returns false
    public boolean addStory(Story s) {
        if (isEmpty()) {
            stories.add(s);
            return true;
        } else if (findStory(s.getName()) == null) {
            int i = 0;
            Story otherStory = stories.get(i);
            while (i < stories.size() && s.getName().compareTo(otherStory.getName()) > 0) {
//                otherStory = stories.get(i);
                i++;
            }
            stories.add(i, s);
//            stories.add(s);
            return true;
        } else {
            return false;
        }
    }

    //    @Override
    public ArrayList<Story> getStories() {
        return stories;
    }

    // EFFECTS: if no stories in library, throws EmptyLibraryException;
    //          else, lists stories in library
    /*public StringBuilder getStories() throws EmptyLibraryException {
        StringBuilder storyList = new StringBuilder();
        if (isEmpty()) {
            throw new EmptyLibraryException();
        } else {
            *//*for (Object s : libraryObj) {
                JSONObject story = (JSONObject) s;
                String storyName = (String) story.get("name");
                storyList.append("\t- ").append(storyName);
            }*//*
            for (Story s : stories) {
                storyList.append("\t- ").append(s.getName());
            }
        }
        return storyList;
    }*/

    // EFFECTS: if no stories in library, null;
    //          else, lists stories in library
    public String[] getStoryNames() /*throws EmptyLibraryException*/ {
        String[] storyList = new String[stories.size()];
        if (isEmpty()) {
//            throw new EmptyLibraryException();
            return null;
        } else {
            /*for (Object s : libraryObj) {
                JSONObject story = (JSONObject) s;
                String storyName = (String) story.get("name");
                storyList.append("\t- ").append(storyName);
            }*/
            for (int i = 0; i < stories.size(); i++) {
                storyList[i] = stories.get(i).getName();
            }
        }
        return storyList;
    }


    // EFFECTS: if library is empty, throw EmptyLibraryException;
    //          if library is not empty but Story is not in library, throw NotAStoryException;
    //          else returns the text;
    public String getStoryText(Story story) throws EmptyLibraryException, NotAStoryException, IOException {
        String fullText;
        if (getSize() == 0) {
            throw new EmptyLibraryException();
        } else if (findStory(story.getName()) != null) {
            fullText = story.getStoryText();
        } else {
            throw new NotAStoryException();
        }
        return fullText;
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
    public int getSize() {
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

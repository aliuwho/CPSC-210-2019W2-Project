package model;

public class WritingPrompt {
    private String prompt;
    private String noun1;
    private String noun2;
    private String verb = selectVerb();
    private String location = selectLocation();

    public static final String PROMPT_START = "Here's your prompt:\nWrite about a ";

    public static final String[] ANIMALS = {"dog", "cat", "horse", "fox", "hamster", "turtle", "dinosaur", "bird"};
    public static final String[] PEOPLE = {"girl", "boy", "wizard",
            "witch", "teacher", "mom", "dad", "grandpa", "grandma", "uncle", "aunt"};
    public static final String[] CREATURES = {"goblin", "dragon", "fairy"};
    public static final String[] OBJECTS = {"book", "car", "truck"};
    public static final String[][] NOUNS = {ANIMALS, PEOPLE, CREATURES, OBJECTS};

    public static final String[] VERBS = {"playing", "running", "fighting", "sitting", "floating", "sleeping"};
    //private String[] adjectives = {};

    public static final String[] OUTDOORS = {"at the beach", "in the woods", "on a mountain", "in a desert"};
    public static final String[] INDOORS = {"in a basement", "in a kitchen", "on a couch", "in a bed"};
    public static final String[][] LOCATIONS = {INDOORS, OUTDOORS};

    // EFFECTS: creates a random prompt
    public WritingPrompt() {
        prompt = this.makePrompt();
    }

    // EFFECTS: returns the prompt
    public String getPrompt() {
        return prompt;
    }

    // EFFECTS: creates a 1 noun prompt if type is "short" and a
    //          2 noun prompt if type is "long"
    public WritingPrompt(String type) {
        if (type.equals("short")) {
            prompt = this.makePromptShort();
        } else {
            prompt = this.makePromptLong();
        }
    }

    // EFFECTS: creates a prompt that either has 1 noun or 2 nouns
    public String makePrompt() {
        int promptType = (int) (Math.random() * 2);
        if (promptType == 1) {
            return makePromptShort();
        } else {
            return makePromptLong();
        }
    }

    // EFFECTS: creates a prompt with 1 noun
    public String makePromptShort() {
        noun1 = selectNoun();
        return PROMPT_START + noun1 + makePreposition();
    }

    // EFFECTS: creates a prompt with 2 nouns
    public String makePromptLong() {
        noun1 = selectNoun();
        noun2 = selectNoun();
        return PROMPT_START + noun1 + " and a " + noun2 + makePreposition();
    }

    // EFFECTS: creates a preposition
    public String makePreposition() {
        return " " + verb + " " + location + ".";
    }

    // EFFECTS: returns a noun from the nouns
    public String selectNoun() {
        int nounType = (int) (Math.random() * NOUNS.length);
        int noun = (int) (Math.random() * NOUNS[nounType].length);
        return NOUNS[nounType][noun];
    }

    // EFFECTS: selects a location from locations
    public String selectLocation() {
        int locType = (int) (Math.random() * LOCATIONS.length);
        int loc = (int) (Math.random() * LOCATIONS[locType].length);
        return LOCATIONS[locType][loc];
    }

    // EFFECTS: selects a verb from verbs
    public String selectVerb() {
        int verb = (int) (Math.random() * VERBS.length);
        return VERBS[verb];
    }

    // EFFECTS: returns noun1
    public String getNoun1() {
        return noun1;
    }

    // EFFECTS: if noun2 is null, returns "There is no noun2."
    //          otherwise, returns noun2
    public String getNoun2() {
        if (noun2 != null) {
            return noun2;
        } else {
            return "There is no noun2.";
        }
    }

    // EFFECTS: returns verb
    public String getVerb() {
        return verb;
    }

    // EFFECTS: returns location
    public String getLocation() {
        return location;
    }

}

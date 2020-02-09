package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PromptTest {
    private Prompt p1;
    private Prompt p2;
    private Prompt shortP;
    private Prompt longP;
    private String[] verbs = Prompt.VERBS;

    @BeforeEach
    public void runBefore() {
        p1 = new Prompt();
        p2 = new Prompt();
        shortP = new Prompt("short");
        longP = new Prompt("long");
    }

    @Test
    public void testMakePreposition() {
        assertEquals(" " + p1.getVerb() + " " + p1.getLocation() + ".", p1.makePreposition());
        assertEquals(" " + p2.getVerb() + " " + p2.getLocation() + ".", p2.makePreposition());
        assertEquals(" " + shortP.getVerb() + " " + shortP.getLocation() + ".", shortP.makePreposition());
        assertEquals(" " + longP.getVerb() + " " + longP.getLocation() + ".", longP.makePreposition());
    }

    @Test
    public void testMakeRandomPrompt() {
        assertNotEquals(p1.getPrompt(), p2.getPrompt());
    }

    @Test
    public void testMakeShortPrompt() {
        assertNotEquals("", shortP.getNoun1());
        assertEquals("There is no noun2.", shortP.getNoun2());
    }

    @Test
    public void testMakeLongPrompt() {
        assertNotEquals("", longP.getNoun1());
        assertNotEquals("", longP.getNoun2());
    }
}
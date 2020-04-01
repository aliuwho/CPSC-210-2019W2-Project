package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WritingPromptTest {
    private WritingPrompt p1;
    private WritingPrompt p2;
//    private WritingPrompt shortP;
//    private WritingPrompt longP;

    @BeforeEach
    public void runBefore() {
        p1 = new WritingPrompt();
        // ensure it is a short prompt
        while(p1.getType()!=0) {
            p1 = new WritingPrompt();
        }
        p2 = new WritingPrompt();
        // ensure it is a long prompt
        while(p2.getType()!=1) {
            p2 = new WritingPrompt();
        }
//        shortP = new WritingPrompt("short");
//        longP = new WritingPrompt("long");
    }

    @Test
    public void testMakePreposition() {
        assertEquals(" " + p1.getVerb() + " " + p1.getLocation() + ".", p1.makePreposition());
        assertEquals(" " + p2.getVerb() + " " + p2.getLocation() + ".", p2.makePreposition());
//        assertEquals(" " + shortP.getVerb() + " " + shortP.getLocation() + ".", shortP.makePreposition());
//        assertEquals(" " + longP.getVerb() + " " + longP.getLocation() + ".", longP.makePreposition());
    }

//    @Test
//    public void testMakeRandomPrompt() {
//        assertNotEquals(p1.getPrompt(), p2.getPrompt());
//    }
//
//    @Test
//    public void testMakeShortPrompt() {
//        assertNotEquals("", shortP.getNoun1());
//        assertEquals("There is no noun2.", shortP.getNoun2());
//    }
//
//    @Test
//    public void testMakeLongPrompt() {
//        assertNotEquals("", longP.getNoun1());
//        assertNotEquals("", longP.getNoun2());
//    }
}
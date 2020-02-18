package model;

import java.util.HashMap;

public class Profile {
    public static final HashMap<String, Integer> keys = new HashMap<String, Integer>();

    public Profile() {
        keys.put("NAME", 0);
        keys.put("POINTS", 1);
        keys.put("LIBRARY", 2);
        keys.put("PETS", 3);
    }

}




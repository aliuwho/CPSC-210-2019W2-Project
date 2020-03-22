package model;

import java.awt.*;

/**
 * represents a chip in a FourBoard
 */
public class Chip {


//    public enum ChipType {
//        EMPTY, RED, BLUE;
//    }

    private final Color type;

//    private String type;

    public Chip(Color color) {
//        this.type = type;
//        if (type.equals(ChipType.RED)) {
//            color = Color.RED;
//        } else if (type.equals(ChipType.BLUE)) {
//            color = Color.BLUE;
//        } else if (type.equals(ChipType.EMPTY)) {
//            color = Color.LIGHT_GRAY;
//        }
        this.type = color;
    }

    public Color getType() {
        return type;
    }

//    public ChipType getType() {
//        return type;
//    }
}

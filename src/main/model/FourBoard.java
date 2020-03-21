package model;

import model.exceptions.ColumnFullException;

import java.awt.*;
import java.util.Arrays;

/**
 * represents a Connect4 board and status
 */
public class FourBoard {
    // note: can use x's and filled circles
    // empty spaces are represented using 0
    // filled spaces are represented using one of:
    //      - 1 (representing a color) (printed using 〇 or X)
    //      - 2 (representing a color different from 2) (printed using ⬤)

    private Chip[][] chips;
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static Color EMPTY_CHIP = Color.LIGHT_GRAY;
    public static Color[] TYPES = {Color.RED, Color.BLUE};

    // EFFECTS: creates a new, empty board
    public FourBoard() {
        chips = new Chip[ROWS][COLS];
        for (Chip[] rows : chips) {
            Arrays.fill(rows, new Chip(EMPTY_CHIP));
        }
    }

    // MODIFIES: this
    // EFFECTS: if canAddChip, adds a chip of chipType to lowest position on board at column and returns true;
    //          otherwise, throws a ColumnFullException
    public void addChip(Chip chip, int column) throws ColumnFullException {
        if (canAddChip(column)) {
            int i = 0;
            while (/*i < ROWS && */!chips[i][column].getType().equals(EMPTY_CHIP)) {
                i++;
            }
            chips[i][column] = chip;
        } else {
            throw new ColumnFullException();
        }
    }

    // EFFECTS: returns ChipType at position board[row][col]
    public Color getChipType(int row, int col) {
        return chips[row][col].getType();
    }

    // EFFECTS: returns Color of chips if there are four identical, non-empty chips in a row;
    //          and null otherwise
    public Color isFourAcross() {
        int count = 0;
        for (Color type : TYPES) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (chips[r][c].getType() == type) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 4) {
                        return type;
                    }
                }
            }
        }
        return null;
    }

    // EFFECTS: returns Color if four identical, non-empty chips in a column in a row, null otherwise
    public Color isFourUpDown() {
        int count = 0;
        for (Color type : TYPES) {
            for (int c = 0; c < COLS; c++) {
                for (int r = 0; r < ROWS; r++) {
                    if (chips[r][c].getType() == type) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 4) {
                        return type;
                    }
                }
            }
        }
        return null;
    }

    // EFFECTS: returns Color of chips if four identical, non-empty chips are in a diagonal;
    // null otherwise
    public Color isFourDiagonal() {
        for (Color type : TYPES) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (chips[r][c].getType() == type && (isRightDiagonal(r, c, type) || isLeftDiagonal(r, c, type))) {
                        return type;
                    }
                }
            }
        }
        return null;
    }

    // EFFECTS: if the column is full, return false;
    //          otherwise return true
    public boolean canAddChip(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (chips[i][col].getType().equals(EMPTY_CHIP)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if there is a right 4 diagonal from given row, col; false otherwise
    public boolean isRightDiagonal(int row, int col, Color target) {
        boolean ret = false;
        int r = row;
        int c = col;
        int count = 0;
        while (r < ROWS && c < COLS) {
            if (chips[r][c].getType().equals(target)) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                ret = true;
            }
            r++;
            c++;
        }
        return ret;
    }

    // EFFECTS: returns true if there is a left 4 diagonal from given row, col; false otherwise
    public boolean isLeftDiagonal(int row, int col, Color target) {
        boolean ret = false;
        int r = row;
        int c = col;
        int count = 0;
        while (r < ROWS && c >= 0) {
            if (chips[r][c].getType().equals(target)) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                ret = true;
            }
            r++;
            c--;
        }
        return ret;
    }

    //EFFECTS: returns chips in board
    public Chip[][] getChips() {
        return chips;
    }

}

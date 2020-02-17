package model;

import model.exceptions.ColumnFullException;

import java.util.Arrays;

public class FourBoard {
    // note: can use x's and filled circles
    // empty spaces are represented using 0
    // filled spaces are represented using one of:
    //      - 1 (representing a color) (printed using 〇 or X)
    //      - 2 (representing a color different from 2) (printed using ⬤)
    int[][] board;
    public static int ROWS = 6;
    public static int COLS = 7;

    // EFFECTS: creates a new, empty board
    public FourBoard() {
        board = new int[ROWS][COLS];
        for (int[] rows : board) {
            Arrays.fill(rows, 0);
        }
    }

    // MODIFIES: this
    // EFFECTS: if canAddChip, adds a chip of chipType to lowest position on board at column and returns true;
    //          otherwise, throws a ColumnFullException
    public void addChip(int chipType, int column) throws ColumnFullException {
        if (canAddChip(column)) {
            int i = 0;
            while (i < ROWS && board[i][column] != 0) {
                i++;
            }
            board[i][column] = chipType;
        } else {
            throw new ColumnFullException();
        }
    }

    // EFFECTS: returns ChipType at position board[row][col]
    public int getChipType(int row, int col) {
        return board[row][col];
    }

    // EFFECTS: returns true if there are four identical, non-empty chips in a row;
    //          and false otherwise
    public boolean isFourAcross() {
        return false; //stub
    }

    // EFFECTS: returns true if four identical, non-empty chips in a column in a  row
    public boolean isFourUpDown() {
        return false; //stub
    }

    // EFFECTS: returns true if four identical, non-empty chips are in a diagonal
    public boolean isFourDiagonal() {
        return false; //stub
    }

    public boolean canAddChip(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][col] == 0) {
                return true;
            }
        }
        return false;
    }


}

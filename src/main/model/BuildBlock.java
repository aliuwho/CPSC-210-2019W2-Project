package model;

import model.exceptions.ColumnFullException;

import java.util.Arrays;

public class BuildBlock {

    public enum Block {
        RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, BLACK, WHITE, EMPTY
    }

    // note: can use x's and filled circles
    // empty spaces are represented using 0
    // filled spaces are represented using one of:
    //      - 1 (representing a color) (printed using 〇 or X)
    //      - 2 (representing a color different from 2) (printed using ⬤)
    private final Block[][] board;
    public static final int ROWS = 21;
    public static final int COLS = 13;

    // EFFECTS: creates a new, empty board
    public BuildBlock() {
        board = new Block[ROWS][COLS];
        for (Block[] rows : board) {
            Arrays.fill(rows, Block.EMPTY);
        }
    }

    // MODIFIES: this
    // EFFECTS: if canAddChip, adds a chip of chipType to lowest position on board at column and returns true;
    //          otherwise, throws a ColumnFullException
    public void addChip(Block block, int column) throws ColumnFullException {
        if (canAddChip(column)) {
            int i = 0;
            while (board[i][column] != BuildBlock.Block.EMPTY) {
                i++;
            }
            board[i][column] = block;
        } else {
            throw new ColumnFullException();
        }
    }

    // EFFECTS: returns block color at position board[row][col]
    public Block getBlockColor(int row, int col) {
        return board[row][col];
    }

    /*// EFFECTS: returns true if there are four identical, non-empty chips in a row;
    //          and false otherwise
    public boolean isFourAcross() {
        int count = 0;
        Chip[] chips = {Chip.RED, Chip.BLUE};
        for (Chip chip : chips) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (board[r][c] == chip) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    /*// EFFECTS: returns true if four identical, non-empty chips in a column in a  row
    public boolean isFourUpDown() {
        int count = 0;
        Chip[] chips = {Chip.RED, Chip.BLUE};
        for (Chip chip : chips) {
            for (int c = 0; c < COLS; c++) {
                for (int r = 0; r < ROWS; r++) {
                    if (board[r][c] == chip) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    /*// EFFECTS: returns true if four identical, non-empty chips are in a diagonal
    public boolean isFourDiagonal() {
        Chip[] chips = {Chip.RED, Chip.BLUE};
        for (Chip chip : chips) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (board[r][c] == chip && (isRightDiagonal(r, c, chip) || isLeftDiagonal(r, c, chip))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    // EFFECTS: if the column is full, return false;
    //          otherwise return true
    public boolean canAddChip(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][col] == Block.EMPTY) {
                return true;
            }
        }
        return false;
    }

    /*// EFFECTS: returns true if there is a right 4 diagonal from given row, col; false otherwise
    public boolean isRightDiagonal(int row, int col, Chip target) {
        boolean ret = false;
        int r = row;
        int c = col;
        int count = 0;
        while (r < ROWS && c < COLS) {
            if (board[r][c] == target) {
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
    }*/

    /*// EFFECTS: returns true if there is a left 4 diagonal from given row, col; false otherwise
    public boolean isLeftDiagonal(int row, int col, Chip target) {
        boolean ret = false;
        int r = row;
        int c = col;
        int count = 0;
        while (r < ROWS && c >= 0) {
            if (board[r][c] == target) {
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
    }*/

}

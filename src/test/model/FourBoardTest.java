package model;

import model.exceptions.ColumnFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FourBoardTest {

    private FourBoard board;

    // note: can use x's and filled circles
    // empty spaces are represented using 0
    // filled spaces are represented using one of:
    //      - 1 (representing a color) (printed using 〇 or X)
    //      - 2 (representing a color different from 2) (printed using ⬤)
    @BeforeEach
    public void runBefore() {
        board = new FourBoard();
    }


    @Test
    public void testAdd1Chip() {
        try {
            board.addChip(1, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertEquals(1, board.getChipType(0, 0));
    }

    @Test
    public void testAddChipException() {
        try {
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
    }

    @Test
    public void testAddMultiChip() {
        try {
            board.addChip(1, 0);
            board.addChip(1, 1);
            board.addChip(1, 3);

            assertEquals(1, board.getChipType(0, 0));
            assertEquals(2, board.getChipType(1, 0));
            assertEquals(1, board.getChipType(3, 0));
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
    }

    @Test
    public void testIsFourAcross() {
        try {
            board.addChip(1, 0);
            board.addChip(1, 1);
            board.addChip(1, 2);
            board.addChip(1, 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertTrue(board.isFourAcross());
    }

    @Test
    public void testIsNotFourAcross() {
        try {
            board.addChip(1, 0);
            board.addChip(2, 1);
            board.addChip(1, 2);
            board.addChip(1, 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertFalse(board.isFourAcross());
    }

    @Test
    public void isNotFourUpDown() {
        try {
            board.addChip(1, 0);
            board.addChip(2, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertFalse(board.isFourUpDown());
    }

    @Test
    public void testIsFourUpDown() {
        try {
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
            board.addChip(1, 0);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourUpDown());
    }

    @Test
    public void testIsFourDiagonal() {
        try {
            board.addChip(1, 0);
            board.addChip(2, 1);
            board.addChip(1, 2);
            board.addChip(1, 3);

            board.addChip(1, 0);
            board.addChip(1, 1);
            board.addChip(2, 0);
            board.addChip(1, 3);

            board.addChip(1, 2);
            board.addChip(2, 3);
            board.addChip(1, 3);
            board.addChip(1, 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourDiagonal());
    }

    @Test
    public void testIsNotFourDiagonal() {
        try {
            board.addChip(1, 0);
            board.addChip(2, 1);
            board.addChip(1, 2);
            board.addChip(1, 3);

            board.addChip(1, 0);
            board.addChip(1, 1);
            board.addChip(2, 0);
            board.addChip(1, 3);

            board.addChip(1, 2);
            board.addChip(2, 3);
            board.addChip(1, 3);
            board.addChip(2, 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertFalse(board.isFourDiagonal());
    }


}

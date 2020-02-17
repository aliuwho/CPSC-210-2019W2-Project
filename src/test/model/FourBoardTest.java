package model;

import model.exceptions.ColumnFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FourBoardTest {

    private FourBoard board;
    private Chip red = Chip.RED;
    private Chip blue = Chip.BLUE;
    private Chip empty = Chip.EMPTY;

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
            board.addChip(red, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertEquals(red, board.getChipType(0, 0));
    }

    @Test
    public void testAddChipException() {
        try {
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
            assertFalse(board.canAddChip(0));
            board.addChip(red, 0);
        } catch (ColumnFullException e) {
            System.out.println("caught column full error");
        }
    }

    @Test
    public void testAddMultiChip() {
        try {
            board.addChip(red, 0);
            board.addChip(blue, 1);
            board.addChip(red, 3);
            board.addChip(red, 0);

            assertEquals(red, board.getChipType(0, 0));
            assertEquals(blue, board.getChipType(0, 1));
            assertEquals(red, board.getChipType(0, 3));
            assertEquals(red, board.getChipType(1, 0));
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
    }

    @Test
    public void testIsFourAcross() {
        try {
            board.addChip(red, 0);
            board.addChip(red, 1);
            board.addChip(red, 2);
            board.addChip(red, 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertTrue(board.isFourAcross());
    }

    @Test
    public void testIsNotFourAcross() {
        try {
            board.addChip(red, 0);
            board.addChip(blue, 1);
            board.addChip(red, 2);
            board.addChip(red, 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertFalse(board.isFourAcross());
    }

    @Test
    public void isNotFourUpDown() {
        try {
            board.addChip(red, 0);
            board.addChip(blue, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertFalse(board.isFourUpDown());
    }

    @Test
    public void testIsFourUpDown() {
        try {
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourUpDown());
    }

    @Test
    public void testIsFourDiagonal() {
        try {
            board.addChip(red, 0);
            board.addChip(blue, 1);
            board.addChip(red, 2);
            board.addChip(red, 3);

            board.addChip(red, 0);
            board.addChip(red, 1);
            board.addChip(blue, 0);
            board.addChip(red, 3);

            board.addChip(red, 2);
            board.addChip(blue, 3);
            board.addChip(red, 3);
            board.addChip(red, 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourDiagonal());
    }

    @Test
    public void testIsNotFourDiagonal() {
        try {
            board.addChip(red, 0);
            board.addChip(blue, 1);
            board.addChip(red, 2);
            board.addChip(red, 3);

            board.addChip(red, 0);
            board.addChip(red, 1);
            board.addChip(blue, 0);
            board.addChip(red, 3);

            board.addChip(red, 2);
            board.addChip(blue, 3);
            board.addChip(red, 3);
            board.addChip(blue, 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertFalse(board.isFourDiagonal());
    }

    @Test
    public void testIsDiagonal() {
        Chip[][] test1 = new Chip[6][7];
        test1[0][0] = red;
        test1[0][1] = blue;
        test1[1][0] = red;
        test1[1][1] = red;
        test1[2][2] = red;
        test1[3][3] = red;
        assertTrue(isDiagonal(test1, 0, 0, red));
        assertFalse(isDiagonal(test1, 0, 0, blue));

        Chip[][] test2 = new Chip[6][7];
        test2[0][1] = blue;
        test2[1][2] = blue;
        test2[2][3] = blue;
        test2[3][4] = blue;
        test2[0][3] = blue;
        assertTrue(isDiagonal(test2, 0, 1, blue));
        assertFalse(isDiagonal(test2, 0, 0, blue));

        Chip[][] test3 = new Chip[6][7];
        test3[3][1] = blue;
        test3[2][2] = blue;
        test3[1][3] = blue;
        test3[0][4] = blue;
        assertTrue(isDiagonal(test3, 0, 4, blue));
        assertFalse(isDiagonal(test3, 0, 1, blue));
    }

    public boolean isDiagonal(Chip[][] nums, int row, int col, Chip target) {
        boolean ret = false;
        int r = row;
        int c = col;
        int count = 0;
        while (r < nums.length && c < nums[r].length) {
            if (nums[r][c] == target) {
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
        count = 0;
        r = row;
        c = col;
        while (r >= 0 && c >=0) {
            if (nums[r][c] == target) {
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

}

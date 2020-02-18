package model;

import model.exceptions.ColumnFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FourBoardTest {

    private FourBoard board;
    private static final Chip RED = Chip.RED;
    private static final Chip BLUE = Chip.BLUE;
//    private static final Chip EMPTY = Chip.EMPTY;

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
            board.addChip(RED, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertEquals(RED, board.getChipType(0, 0));
    }

    @Test
    public void testAddChipException() {
        try {
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            assertFalse(board.canAddChip(0));
            board.addChip(RED, 0);
        } catch (ColumnFullException e) {
            System.out.println("caught column full error");
        }
    }

    @Test
    public void testAddMultiChip() {
        try {
            board.addChip(RED, 0);
            board.addChip(BLUE, 1);
            board.addChip(RED, 3);
            board.addChip(RED, 0);

            assertEquals(RED, board.getChipType(0, 0));
            assertEquals(BLUE, board.getChipType(0, 1));
            assertEquals(RED, board.getChipType(0, 3));
            assertEquals(RED, board.getChipType(1, 0));
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
    }

    @Test
    public void testIsFourAcross() {
        try {
            board.addChip(RED, 0);
            board.addChip(RED, 1);
            board.addChip(RED, 2);
            board.addChip(RED, 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertTrue(board.isFourAcross());
    }

    @Test
    public void testIsNotFourAcross() {
        try {
            board.addChip(RED, 0);
            board.addChip(BLUE, 1);
            board.addChip(RED, 2);
            board.addChip(RED, 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertFalse(board.isFourAcross());
    }

    @Test
    public void isNotFourUpDown() {
        try {
            board.addChip(RED, 0);
            board.addChip(BLUE, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertFalse(board.isFourUpDown());
    }

    @Test
    public void testIsFourUpDown() {
        try {
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
            board.addChip(RED, 0);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourUpDown());
    }

    @Test
    public void testIsFourRightDiagonal() {
        try {
            board.addChip(RED, 0);
            board.addChip(BLUE, 1);
            board.addChip(RED, 2);
            board.addChip(RED, 3);

            board.addChip(RED, 0);
            board.addChip(RED, 1);
            board.addChip(BLUE, 0);
            board.addChip(RED, 3);

            board.addChip(RED, 2);
            board.addChip(BLUE, 3);
            board.addChip(RED, 3);
            board.addChip(RED, 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourDiagonal());
    }

    @Test
    public void testIsFourLeftDiagonal() {
        try {
            board.addChip(RED, 2);
            board.addChip(BLUE, 2);
            board.addChip(RED, 2);
            board.addChip(RED, 2);

            board.addChip(RED, 2);
            board.addChip(RED, 3);
            board.addChip(BLUE, 3);
            board.addChip(RED, 1);

            board.addChip(RED, 3);
            board.addChip(BLUE, 4);
            board.addChip(RED, 4);
            board.addChip(RED, 5);
        } catch (ColumnFullException e) {
            fail();
        }
        assertTrue(board.isFourDiagonal());
    }

    @Test
    public void testIsNotFourDiagonal() {
        try {
            board.addChip(RED, 0);
            board.addChip(BLUE, 1);
            board.addChip(RED, 2);
            board.addChip(RED, 3);

            board.addChip(RED, 0);
            board.addChip(RED, 1);
            board.addChip(BLUE, 0);
            board.addChip(RED, 3);

            board.addChip(RED, 2);
            board.addChip(BLUE, 3);
            board.addChip(RED, 3);
            board.addChip(BLUE, 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertFalse(board.isFourDiagonal());
    }

    @Test
    public void testIsDiagonal() {
        Chip[][] test1 = new Chip[6][7];
        test1[0][0] = RED;
        test1[0][1] = BLUE;
        test1[1][0] = RED;
        test1[1][1] = RED;
        test1[2][2] = RED;
        test1[3][3] = RED;
        assertTrue(isDiagonal(test1, 0, 0, RED));
        assertFalse(isDiagonal(test1, 0, 0, BLUE));

        Chip[][] test2 = new Chip[6][7];
        test2[0][1] = BLUE;
        test2[1][2] = BLUE;
        test2[2][3] = BLUE;
        test2[3][4] = BLUE;
        test2[0][3] = BLUE;
        assertTrue(isDiagonal(test2, 0, 1, BLUE));
        assertFalse(isDiagonal(test2, 0, 0, BLUE));

        Chip[][] test3 = new Chip[6][7];
        test3[3][1] = BLUE;
        test3[2][2] = BLUE;
        test3[1][3] = BLUE;
        test3[0][4] = BLUE;
        assertTrue(isDiagonal(test3, 0, 4, BLUE));
        assertFalse(isDiagonal(test3, 0, 1, BLUE));
    }

    public boolean isDiagonal(Chip[][] chips, int row, int col, Chip target) {
        boolean ret = false;
        int r = row;
        int c = col;
        int count = 0;
        while (r < chips.length && c < chips[r].length) {
            if (chips[r][c] == target) {
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
        while (r >= 0 && c >= 0) {
            if (chips[r][c] == target) {
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

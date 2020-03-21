package model;

import model.exceptions.ColumnFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class FourBoardTest {

    private FourBoard board;
    //    private static final Chip.ChipType RED = Chip.ChipType.RED;
//    private static final Chip.ChipType BLUE = Chip.ChipType.BLUE;
//    private static final Chip EMPTY = Chip.EMPTY;
    private static final Color RED = Color.RED;
    private static final Color BLUE = Color.BLUE;

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
            board.addChip(new Chip(RED), 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertEquals(RED, board.getChipType(0, 0));
    }

    @Test
    public void testAddChipException() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            assertFalse(board.canAddChip(0));
            board.addChip(new Chip(RED), 0);
        } catch (ColumnFullException e) {
            System.out.println("caught column full error");
        }
    }

    @Test
    public void testAddMultiChip() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(BLUE), 1);
            board.addChip(new Chip(RED), 3);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(BLUE), 0);

            assertEquals(RED, board.getChipType(0, 0));
            assertEquals(BLUE, board.getChipType(0, 1));
            assertEquals(RED, board.getChipType(0, 3));
            assertEquals(RED, board.getChipType(1, 0));
            assertEquals(RED, board.getChipType(2, 0));
            assertEquals(RED, board.getChipType(3, 0));
            assertEquals(RED, board.getChipType(4, 0));
            assertEquals(BLUE, board.getChipType(5, 0));
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
    }

    @Test
    public void testIsFourAcross() {
        try {
            board.addChip(new Chip(BLUE), 0);
            board.addChip(new Chip(BLUE), 1);
            board.addChip(new Chip(BLUE), 2);
            board.addChip(new Chip(BLUE), 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertEquals(BLUE, board.isFourAcross());
    }

    @Test
    public void testIsNotFourAcross() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(BLUE), 1);
            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(RED), 3);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertNull(board.isFourAcross());
    }

    @Test
    public void isNotFourUpDown() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(BLUE), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full exception");
        }
        assertNull(board.isFourUpDown());
    }

    @Test
    public void testIsFourUpDown() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 0);
        } catch (ColumnFullException e) {
            fail();
        }
        assertEquals(RED, board.isFourUpDown());

        board = new FourBoard();
        try {
            board.addChip(new Chip(BLUE), 2);
            board.addChip(new Chip(BLUE), 2);
            board.addChip(new Chip(BLUE), 2);
            board.addChip(new Chip(BLUE), 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertEquals(BLUE, board.isFourUpDown());
    }

    @Test
    public void testIsFourRightDiagonal() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(BLUE), 1);
            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(RED), 3);

            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 1);
            board.addChip(new Chip(BLUE), 0);
            board.addChip(new Chip(RED), 3);

            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(BLUE), 3);
            board.addChip(new Chip(RED), 3);
            board.addChip(new Chip(RED), 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertEquals(RED, board.isFourDiagonal());
    }

    @Test
    public void testIsFourLeftDiagonal() {
        try {
            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(BLUE), 2);
            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(RED), 2);

            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(RED), 3);
            board.addChip(new Chip(BLUE), 3);
            board.addChip(new Chip(RED), 1);

            board.addChip(new Chip(RED), 3);
            board.addChip(new Chip(BLUE), 4);
            board.addChip(new Chip(RED), 4);
            board.addChip(new Chip(RED), 5);
        } catch (ColumnFullException e) {
            fail();
        }
        assertEquals(RED, board.isFourDiagonal());
    }

    @Test
    public void testIsNotFourDiagonal() {
        try {
            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(BLUE), 1);
            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(RED), 3);

            board.addChip(new Chip(RED), 0);
            board.addChip(new Chip(RED), 1);
            board.addChip(new Chip(BLUE), 0);
            board.addChip(new Chip(RED), 3);

            board.addChip(new Chip(RED), 2);
            board.addChip(new Chip(BLUE), 3);
            board.addChip(new Chip(RED), 3);
            board.addChip(new Chip(BLUE), 2);
        } catch (ColumnFullException e) {
            fail();
        }
        assertNull(board.isFourDiagonal());
    }

//    @Test
//    public void testIsDiagonal() {
//        Chip[][] test1 = new Chip[6][7];
//        test1[0][0] = new Chip(RED);
//        test1[0][1] = new Chip(BLUE);
//        test1[1][0] = new Chip(RED);
//        test1[1][1] = new Chip(RED);
//        test1[2][2] = new Chip(RED);
//        test1[3][3] = new Chip(RED);
//        assertTrue(isDiagonal(test1, 0, 0, RED));
//        assertFalse(isDiagonal(test1, 0, 0, BLUE));
//
//        Chip[][] test2 = new Chip[6][7];
//        test2[0][1] = new Chip(BLUE);
//        test2[1][2] = new Chip(BLUE);
//        test2[2][3] = new Chip(BLUE);
//        test2[3][4] = new Chip(BLUE);
//        test2[0][3] = new Chip(BLUE);
//        assertTrue(isDiagonal(test2, 0, 1, BLUE));
//        assertFalse(isDiagonal(test2, 0, 0, BLUE));
//
//        Chip[][] test3 = new Chip[6][7];
//        test3[3][1] = new Chip(BLUE);
//        test3[2][2] = new Chip(BLUE);
//        test3[1][3] = new Chip(BLUE);
//        test3[0][4] = new Chip(BLUE);
//        assertTrue(isDiagonal(test3, 0, 4, BLUE));
//        assertFalse(isDiagonal(test3, 0, 1, BLUE));
//    }
//
//    public boolean isDiagonal(Chip[][] chips, int row, int col, Color target) {
//        boolean ret = false;
//        int r = row;
//        int c = col;
//        int count = 0;
//        while (r < chips.length && c < chips[r].length) {
//            if (chips[r][c] != null && chips[r][c].getType().equals(target)) {
//                count++;
//            } else {
//                count = 0;
//            }
//            if (count == 4) {
//                ret = true;
//            }
//            r++;
//            c++;
//        }
//        count = 0;
//        r = row;
//        c = col;
//        while (r >= 0 && c >= 0) {
//            if (chips[r][c] != null && chips[r][c].getType().equals(target)) {
//                count++;
//            } else {
//                count = 0;
//            }
//            if (count == 4) {
//                ret = true;
//            }
//            r++;
//            c--;
//        }
//        return ret;
//    }

}

package model;

import model.exceptions.ColumnFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuildBlockTest {

    private BuildBlock board;
    private BuildBlock.Block red = BuildBlock.Block.RED;
    private BuildBlock.Block blue = BuildBlock.Block.BLUE;
//    private BuildingBlock.Block empty = BuildingBlock.Block.EMPTY;

    @BeforeEach
    public void runBefore() {
        board = new BuildBlock();
    }
/*
    @Test
    public void testBlocks() {
        for (BuildingBlock.Block b : BuildingBlock.Block.values()) {
            //do something
        }
    }*/


    @Test
    public void testAdd1Chip() {
        try {
            board.addChip(red, 0);
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
        assertEquals(red, board.getBlockColor(0, 0));
    }

    @Test
    public void testAddChipException() {
        try {
            for (int i = 0; i < BuildBlock.ROWS; i++) {
                board.addChip(blue, 0);
            }
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
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(red, 0);
            board.addChip(blue, 0);

            assertEquals(red, board.getBlockColor(0, 0));
            assertEquals(blue, board.getBlockColor(0, 1));
            assertEquals(red, board.getBlockColor(0, 3));
            assertEquals(red, board.getBlockColor(1, 0));
            assertEquals(red, board.getBlockColor(2, 0));
            assertEquals(red, board.getBlockColor(3, 0));
            assertEquals(red, board.getBlockColor(4, 0));
            assertEquals(blue, board.getBlockColor(5, 0));
        } catch (ColumnFullException e) {
            fail("unexpected column full error");
        }
    }

    /*@Test
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
    public void testIsFourRightDiagonal() {
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
    public void testIsFourLeftDiagonal() {
        try {
            board.addChip(red, 2);
            board.addChip(blue, 2);
            board.addChip(red, 2);
            board.addChip(red, 2);

            board.addChip(red, 2);
            board.addChip(red, 3);
            board.addChip(blue, 3);
            board.addChip(red, 1);

            board.addChip(red, 3);
            board.addChip(blue, 4);
            board.addChip(red, 4);
            board.addChip(red, 5);
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
*/
}

package com.computationlogic;

import com.problemdomaine.SudokuGame;

public class GameGenerator {
    private static final int[][][] newGrid = new int[9][9][2];

    public static int[][][] getNewGameGrid(){
        return unsolveGame(getSolvedGame());
    }

    private static int[][][] unsolveGame(int[][][] solvedGame) {
        removeKDigits(50);
        return newGrid;
    }

    public static int[][][] getSolvedGame(){
        SudokuUtilities.Initialize(newGrid);
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, 3);

        return newGrid;
    }


    // Fill the diagonal SRN number of SRN x SRN matrices
    static void fillDiagonal()
    {

        for (int i = 0; i<9; i=i+3)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    static boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (newGrid[rowStart+i][colStart+j][0]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    static void fillBox(int row, int col)
    {
        int num;
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                do
                {
                    num = randomGenerator(9);
                }
                while (!unUsedInBox(row, col, num));

                newGrid[row+i][col+j][0] = num;
            }
        }
    }

    // Random generator
    static int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    static boolean checkIfSafe(int i, int j, int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%3, j-j%3, num));
    }

    // check in the row for existence
    static boolean unUsedInRow(int i, int num)
    {
        for (int j = 0; j<9; j++)
            if (newGrid[i][j][0] == num)
                return false;
        return true;
    }

    // check in the row for existence
    static boolean unUsedInCol(int j, int num)
    {
        for (int i = 0; i<9; i++)
            if (newGrid[i][j][0] == num)
                return false;
        return true;
    }


    static boolean fillRemaining(int i, int j)
    {
        if (j>=9 && i<9-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=9 && j>=9)
            return true;

        if (i < 3)
        {
            if (j < 3)
                j = 3;
        }
        else if (i < 9-3)
        {
            if (j==(int)(i/3)*3)
                j =  j + 3;
        }
        else
        {
            if (j == 9-3)
            {
                i = i + 1;
                j = 0;
                if (i>=9)
                    return true;
            }
        }

        for (int num = 1; num<=9; num++)
        {
            if (checkIfSafe(i, j, num))
            {
                newGrid[i][j][0] = num;
                if (fillRemaining(i, j+1))
                    return true;

                newGrid[i][j][0] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public static void removeKDigits(int count)
    {
        while (count != 0)
        {
            int cellId = randomGenerator(9*9)-1;

            // extract coordinates i  and j
            int i = (cellId/9);
            int j = cellId%9;
            if (newGrid[i][j][0] != 0)
            {
                count--;
                newGrid[i][j][0] = 0;
                newGrid[i][j][1] = 1;
            }
        }
    }

}

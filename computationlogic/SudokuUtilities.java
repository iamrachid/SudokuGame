package com.computationlogic;

import com.problemdomaine.SudokuGame;

public class SudokuUtilities {
    public static void copySudokuArrayValues(int[][][] oldArray, int[][][] newArray){
        for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUNDARY; xIndex++){
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++){
                newArray[xIndex][yIndex][0] = oldArray[xIndex][yIndex][0];
                newArray[xIndex][yIndex][1] = oldArray[xIndex][yIndex][1];
            }
        }
    }

    public static void Initialize(int[][][] array){
        for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUNDARY; xIndex++){
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++){
                array[xIndex][yIndex][0] = 0;
                array[xIndex][yIndex][1] = 0;
            }
        }
    }


    public static int[][][] copyToNewArray(int[][][] oldArray) {
        int[][][] newArray = new int[SudokuGame.GRID_BOUNDARY][SudokuGame.GRID_BOUNDARY][2];
        copySudokuArrayValues(oldArray, newArray);
        return newArray;
    }

    public static void resetGrid(int[][][] array) {
        for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUNDARY; xIndex++){
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++){
                if (array[xIndex][yIndex][1] == 1)
                    array[xIndex][yIndex][0] = 0;
            }
        }
    }

}

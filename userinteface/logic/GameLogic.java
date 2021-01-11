package com.userinteface.logic;

import com.computationlogic.GameGenerator;
import com.constants.GameState;
import com.problemdomaine.SudokuGame;

import java.util.ArrayList;

import static com.problemdomaine.SudokuGame.BOX_BOUNDARY;
import static com.problemdomaine.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid());
    }

    public static GameState checkForCompletion(int[][][] grid){
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if (tilesAreNotFilled(grid)) {
            return GameState.ACTIVE;
        }
        return GameState.COMPLETE;
    }

    private static boolean sudokuIsInvalid(int[][][] grid) {
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
        if (boxesAreInvalid(grid)) return true;
        return false;
    }

    private static boolean boxesAreInvalid(int[][][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex+=BOX_BOUNDARY) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex+=BOX_BOUNDARY) {
                if (!boxCheck(grid, xIndex, yIndex)) return true;
            }
        }
        return false;
    }

    private static boolean boxCheck(int[][][] grid, int xstart, int ystart) {
        ArrayList<Integer> list = new ArrayList<>();
        fillArrayList(list);
        for (int xIndex = xstart; xIndex < xstart + BOX_BOUNDARY; xIndex++) {
            for (int yIndex = ystart; yIndex < BOX_BOUNDARY; yIndex++) {
                list.remove(Integer.valueOf(grid[xIndex][yIndex][0]));
            }
        }
        if (!list.isEmpty()) return false;
        return true;
    }

    private static boolean rowsAreInvalid(int[][][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            ArrayList<Integer> list = new ArrayList<>();
            fillArrayList(list);
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                list.remove(Integer.valueOf(grid[xIndex][yIndex][0]));
            }
            if (!list.isEmpty()) return true;
        }
        return false;
    }

    private static boolean columnsAreInvalid(int[][][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
            ArrayList<Integer> list = new ArrayList<>();
            fillArrayList(list);
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
                list.remove(Integer.valueOf(grid[xIndex][yIndex][0]));
            }
            if (!list.isEmpty()) return true;
        }
        return false;
    }

    private static void fillArrayList(ArrayList<Integer> list) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++)
            list.add(xIndex);
    }

    private static boolean tilesAreNotFilled(int[][][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex][0] == 0) return true;
            }
        }
        return false;
    }

}

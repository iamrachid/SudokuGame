package com.problemdomaine;

import com.computationlogic.SudokuUtilities;
import com.constants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable {
    private final GameState gameState;
    private int[][][] gridState;
    
    public static final int GRID_BOUNDARY = 9;
    public static final int BOX_BOUNDARY = 9;

    public SudokuGame(GameState gameState, int[][][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }


    public GameState getGameState() {
        return gameState;
    }

    public int[][][] getGridState() {
        return gridState;
    }
}

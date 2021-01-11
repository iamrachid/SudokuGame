package com.userinteface.logic;

import com.computationlogic.SudokuUtilities;
import com.constants.GameState;
import com.constants.Messages;
import com.problemdomaine.IStorage;
import com.problemdomaine.SudokuGame;
import com.userinteface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private final IStorage storage;
    private final IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][][] newGridState = gameData.getGridState();
            newGridState[x][y][0] = input;
            gameData = new SudokuGame(
                    GameLogic.checkForCompletion(newGridState),
                    newGridState);
            storage.updateGame(gameData);
            view.updateSquare(x, y, input);
            if (gameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }
        } catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void newGame() {
        try {
            storage.updateGame(
                GameLogic.getNewGame()
            );
            view.updateBoard(storage.getGameData());
        } catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void resetGame() {
        try {
            SudokuGame game = storage.getGameData();
            SudokuUtilities.resetGrid(game.getGridState());
            storage.updateGame(game);
            view.updateBoard(storage.getGameData());
        } catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

}

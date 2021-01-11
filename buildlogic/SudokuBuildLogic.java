package com.buildlogic;

import com.persistance.LocalStorageImpl;
import com.problemdomaine.IStorage;
import com.problemdomaine.SudokuGame;
import com.userinteface.IUserInterfaceContract;
import com.userinteface.logic.ControlLogic;
import com.userinteface.logic.GameLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGame(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}

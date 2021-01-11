package com.persistance;

import com.problemdomaine.IStorage;
import com.problemdomaine.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {

    private static final File GAME_DATA = new File(
            System.getProperty("user.home"),
            "game.txt"
    );

    @Override
    public void updateGame(SudokuGame game) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        } catch (IOException e){
            throw new IOException("enable to access game data");
        }
        
    }

    @Override
    public SudokuGame getGameData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("File not found");
        }
    }
}

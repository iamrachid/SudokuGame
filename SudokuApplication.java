package com;

import com.buildlogic.SudokuBuildLogic;
import com.userinteface.IUserInterfaceContract;
import com.userinteface.UserInterfaceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        IUserInterfaceContract.View uiImpl = new UserInterfaceImpl(primaryStage);
        try {
            SudokuBuildLogic.build(uiImpl);
        } catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
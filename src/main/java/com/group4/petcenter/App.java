package com.group4.petcenter;

import com.group4.petcenter.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage){
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
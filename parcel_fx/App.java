package com.epl.parcel_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private final String APP_NAME = "Gestion Parcelle";
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle(APP_NAME);
        
        Scene s = new Scene(new MainView(),600, 500);
        
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

}
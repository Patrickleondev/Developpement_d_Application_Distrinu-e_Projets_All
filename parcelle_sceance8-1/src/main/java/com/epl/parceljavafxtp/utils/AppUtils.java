/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextFormatter;
import javafx.util.Pair;

/**
 *
 * @author BELL
 */
public class AppUtils {
    
    public static final String errMsg = "Champs obligatoire non remplis !";
    public static final List<Pair<String, String>> CropShapeData = new ArrayList<>();
    static {
            CropShapeData.add(new Pair("Triangle", "TRIANGLE"));
            CropShapeData.add(new Pair("Cercle", "CERCLE"));
            CropShapeData.add(new Pair("Rectangle", "RECTANGLE"));
    };
    public static TextFormatter getNumberTextFormater(){
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            
            return newText.matches("\\d*(\\.\\d*)?") ? change : null;
        });
     
    }
    // Afficher un message de succès
    public static  void getSuccesAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void printToLog(String text){
        System.out.println(text);
    }
    
    public static void getErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de validation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Message de confirmation lors de la suppression
    public static boolean getConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}



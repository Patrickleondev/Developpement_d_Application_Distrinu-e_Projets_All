/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parcel_fx;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author BELL
 */
public class InfoScreen {
    private Stage infoScreenStage = new Stage();

    public InfoScreen() {
        infoScreenStage.setTitle("Infos");
    }
    
    public Stage getInfoScreenStage(){
        formView formView = new formView();
        Scene s = new Scene(formView.getFormContent());
        infoScreenStage.setScene(s);
        return infoScreenStage;
    }
}

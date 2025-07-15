/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parcel_fx;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author BELL
 */
public class MainView extends BorderPane {
    
    public MainView() {
        super();
        setTop(new TopView());
        setCenter(new ResultView());
    }
    
}

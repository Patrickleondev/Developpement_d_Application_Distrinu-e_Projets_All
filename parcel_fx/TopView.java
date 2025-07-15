/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parcel_fx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author BELL
 */
public class TopView extends HBox {
    
    private Text label = new Text("Entrer la superficie");
    private TextField superficieTF = new TextField();
    
    private Button btn = new Button("Suivant");
    
    
    public TopView() {
        super(20);
        setPadding(new Insets(30));
        btn.setAlignment(Pos.CENTER_RIGHT);
        btn.setOnAction( (e)->{
            InfoScreen i = new InfoScreen();
            i.getInfoScreenStage().show();
           // i.getInfoScreenStage().setAlwaysOnTop(true);
            btn.setDisable(false);
        });
        getChildren().addAll(label,superficieTF,btn);
        setAlignment(Pos.CENTER);
    }
    
 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parcel_fx;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;
import javafx.util.StringConverter;

/**
 *
 * @author BELL
 */
public class formView {
    private VBox vb = new VBox();
    private GridPane formContent = new GridPane();
    private final ChoiceBox<Pair<String, String>> formCB = new ChoiceBox<>();
    private TextField nomTF = new TextField();
    private String labelText = "le rayon du cercle"; 
    private TextField dim1TF = new TextField();
    private Text dim1Label = new Text("");
    private Text dim2Label =new  Text("");
    private TextField dim2TF = new TextField();
    private Button submitBtn = new Button("Valider");
    public formView() {
        super();
        
        formContent.setHgap(100);
        formContent.setVgap(20);

        formContent.add(new Text("Entrer le nom de la culture"), 0, 0);
        formContent.add(nomTF, 1, 0);
        formContent.add(new Text("Sélectionnner une figure"), 0, 2);
        formContent.add(dim1Label, 0, 3);
        formContent.add(dim1TF, 1, 3);
        formContent.add(dim2Label, 0, 4);
        formContent.add(dim2TF, 1, 4);
        

        /*formCB.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Pair<String, String>> ov, Pair<String, String> t, Pair<String, String> t1) -> {
            System.out.println("changed");
        });
        */
        formCB.setPrefWidth(200);
        formCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pair<String, String>>() {

            @Override
            public void changed(ObservableValue<? extends Pair<String, String>> ov, Pair<String, String> oldVal, Pair<String, String> newVal) {
                
                if (newVal!=null) {
                    System.out.println(newVal.getKey());
                    switch (newVal.getValue()) {
                        case "TRI":
                            dim1Label.setText("Entrer la longueur du coté");
                            dim2Label.setText("");
                            dim2TF.setDisable(true);
                            break;
                        case "CER":
                            dim1Label.setText("Entrer le rayon du cercle");
                            dim2Label.setText("");
                            dim2TF.setDisable(true);
                            break;
                        case "REC":
                            dim1Label.setText("Entrer la longueur du rectangle");
                            dim2Label.setText("Entrer la largeur du rectangle");
                            dim2TF.setDisable(false);
                            break;
                        case "":
                            dim1Label.setText("Entrer la longueur du rectangle");
                            dim2Label.setText("Entrer la largeur du rectangle");
                            dim2TF.setDisable(false);
                            break;
                        default:
                            dim1Label.setText("Entrer le rayon du cercle");
                            dim2Label.setText("");
                            dim2TF.setDisable(true);
                    }
                }
                
            }
        });
        
        formContent.add(formCB, 1, 2);
        initChoice();
        submitBtn.setAlignment(Pos.CENTER);
        submitBtn.setPrefWidth(100);
        vb.getChildren().addAll(formContent,submitBtn);
        vb.setPadding(new Insets(30));
        vb.setSpacing(20);
    }

    public VBox getFormContent() {
        return this.vb;
    }

    private final static Pair<String, String> DEFAULT_PAIR = new Pair<>("Rectangle", "REC");

    private void initChoice() {

        List<Pair<String, String>> figData = new ArrayList<>();
        figData.add(new Pair("Triangle", "TRI"));
        figData.add(new Pair("Cercle", "CER"));
        figData.add(new Pair("Rectangle", "REC"));

        formCB.setConverter(new StringConverter<Pair<String, String>>() {
            @Override
            public String toString(Pair<String, String> pair) {
                return pair.getKey();
            }

            @Override
            public Pair<String, String> fromString(String string) {
                return null;
            }
        });
        //formCB.getItems().add(DEFAULT_PAIR);
        formCB.getItems().addAll(figData);
        formCB.setValue(DEFAULT_PAIR);

    }

}

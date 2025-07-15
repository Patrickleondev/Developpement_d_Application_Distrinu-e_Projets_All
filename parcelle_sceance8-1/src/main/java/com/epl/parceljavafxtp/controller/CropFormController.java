/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.epl.parceljavafxtp.controller;

import com.epl.parceljavafxtp.model.CropModel;
import com.epl.parceljavafxtp.model.ManageCropModel;
import com.epl.parceljavafxtp.model.RectangleCrop;
import com.epl.parceljavafxtp.utils.AppUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author BELL
 */
public class CropFormController implements Initializable {

    @FXML
    private Label dim1Label;

    @FXML
    private TextField dim1TF;

    @FXML
    private Label dim2Label;

    @FXML
    private TextField dim2TF;

    @FXML
    private Button exitParcelleConfigBtn;

    @FXML
    private TextField nameTF;

    @FXML
    private ChoiceBox<Pair<String, String>> shapeChoiceBox;

    private final static Pair<String, String> DEFAULT_PAIR = new Pair<>("Rectangle", "RECTANGLE");

    @FXML
    void handleAddCrop(ActionEvent event) {
        if (nameTF.getText().isEmpty()) {
            AppUtils.getErrorAlert(AppUtils.errMsg);
            return;
        }
        
        if (dim1TF.getText().isEmpty()) {
            AppUtils.getErrorAlert(AppUtils.errMsg);
            return;
        }
        
        if ("RECTANGLE".equals(shapeChoiceBox.getValue().getValue()) && dim2TF.getText().isEmpty()) {
            AppUtils.getErrorAlert("Champs obligatoire non remplis !");
            return;
        }

        ManageCropModel manager = ManageCropModel.getInstance();

        manager.addCrop(
                nameTF.getText(),
                shapeChoiceBox.getValue().getValue(),
                Double.parseDouble(dim1TF.getText()),
                dim2TF == null | "".equals(dim2TF.getText()) ?
                        0.0 : Double.parseDouble(dim2TF.getText())
        );
        
        resetForm();
        
        
        AppUtils.printToLog("Avant tri :");
        manager.getCrops();
        manager.setResultmsg();
        AppUtils.getSuccesAlert("Formulaire validé avec succès !");

    }

    @FXML
    void handleExitAddCrop(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameTF.setPromptText("nom");
        dim1TF.setTextFormatter(AppUtils.getNumberTextFormater());
        dim2TF.setTextFormatter(AppUtils.getNumberTextFormater());
        
        shapeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pair<String, String>>() {

            @Override
            public void changed(ObservableValue<? extends Pair<String, String>> ov, Pair<String, String> oldVal, Pair<String, String> newVal) {

                if (newVal != null) {
                    switch (newVal.getValue()) {
                        case "TRIANGLE":
                            dim1Label.setText("Entrer la longueur du coté");
                            dim2Label.setText("");
                            dim1TF.setPromptText("Coté");
                            dim2TF.setText("");
                            dim2TF.setDisable(true);
                            break;
                        case "CERCLE":
                            dim1Label.setText("Entrer le rayon du cercle");
                            dim2Label.setText("");
                            dim1TF.setPromptText("Rayon");
                            dim2TF.setText("");
                            dim2TF.setDisable(true);
                            break;
                        case "RECTANGLE":
                            dim1Label.setText("Entrer la longueur du rectangle");
                            dim2Label.setText("Entrer la largeur du rectangle");
                            dim1TF.setPromptText("Longeur");
                            dim2TF.setPromptText("Largeur");
                            dim2TF.setText("");
                            dim2TF.setDisable(false);
                            break;
                        default:
                            dim1Label.setText("Entrer le rayon du cercle");
                            dim2Label.setText("");
                            dim1TF.setPromptText("Rayon");
                            dim2TF.setText("");
                            dim2TF.setDisable(true);
                    }
                }

            }
        });

        initChoice(AppUtils.CropShapeData);
    }    
    
    private void resetForm(){
        dim1TF.setText("");
        dim2TF.setText("");
        nameTF.setText("");
        List<Pair<String, String>> emptyList = new ArrayList<>();
        initChoice(emptyList);
    }

    private void initChoice(List<Pair<String, String>>  figData) {

        shapeChoiceBox.setConverter(new StringConverter<Pair<String, String>>() {
            @Override
            public String toString(Pair<String, String> pair) {
                return pair.getKey();
            }
            @Override
            public Pair<String, String> fromString(String string) {
                return null;
            }
        });
        
        if (figData.isEmpty()) {
            return;
        }
        shapeChoiceBox.getItems().addAll(figData);
        shapeChoiceBox.setValue(DEFAULT_PAIR);
    }
}

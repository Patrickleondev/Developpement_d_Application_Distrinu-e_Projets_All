/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.epl.parceljavafxtp.controller;

import com.epl.parceljavafxtp.App;
import com.epl.parceljavafxtp.model.CropModel;
import com.epl.parceljavafxtp.model.ManageCropModel;
import com.epl.parceljavafxtp.utils.AppUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author BELL
 */
public class HomePageController implements Initializable {

    @FXML
    private AnchorPane anId;
    
    @FXML
    private TableView<CropModel> cropTableId;
    
    @FXML
    private TableColumn<CropModel, Double> areaTableCol;
    
    @FXML
    private TableColumn<CropModel, String> nameTableCol;
    
    @FXML
    private TableColumn<CropModel, String> shapeTableCol;

    @FXML
    private TableColumn<CropModel, Void> deleteTableCol;

    @FXML
    private Button editGolbalAreaBtnId;

    @FXML
    private TextField globalAreaId;

    @FXML
    private Text globalAreaValueLabel;

    @FXML
    private Button handleGamingSpace;
    
    @FXML
    private Text resultLabelId;
    
    @FXML
    private Button deleteAll;

    

    @FXML
    void handleEditGolbalArea(ActionEvent event) {
        if (globalAreaValueLabel.getText().isEmpty()) {
          AppUtils.getErrorAlert(AppUtils.errMsg);
          return;
        }
        //globalAreaValueLabel.setText(globalAreaId.getText());
        ManageCropModel.getInstance().setGlobalArea(globalAreaId.getText());
        //ManageCropModel.getInstance().setResultmsg();
    }

    @FXML
    void handleGamingSpace(ActionEvent event) {

    }

    @FXML
    void handleParcelleInfoForm(ActionEvent event) throws IOException {
        App.handleNewPage("cropForm","Ajout de culture");
    }
    
    @FXML
    void deleteAll(ActionEvent event) {
        boolean confirm = AppUtils.getConfirmationAlert("Confirmer la suppression", 
                        "Êtes-vous sûr de vouloir supprimer toutes les cultures ?");

        if (confirm) {
            ManageCropModel.getInstance().clearCrops();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        globalAreaId.setTextFormatter(AppUtils.getNumberTextFormater());
        
        resultLabelId.textProperty().bind(ManageCropModel.getInstance().getResultmsg());
        
        globalAreaValueLabel.textProperty().bind(ManageCropModel.getInstance().getGlobalArea());
        
        
        nameTableCol.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getCropName()
        ));
        
        shapeTableCol.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getShapeName()
        ));
        
        areaTableCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().area()
        ).asObject());
        
        cropTableId.setItems(ManageCropModel.getInstance().getCrops());
        
        deleteTableCol.setCellFactory(col -> new TableCell<CropModel, Void>() {
        private final Button deleteBtn = new Button();
        private final ImageView deleteIcon = new ImageView(
            new Image(getClass().getResourceAsStream("/images/delete.png"))
        );

        {   deleteBtn.setPrefSize(30, 30);
            deleteIcon.setFitWidth(30);
            deleteIcon.setFitHeight(30);
            deleteBtn.setGraphic(deleteIcon);
            deleteBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand");


            deleteBtn.setOnAction(e -> {
                CropModel crop = getTableView().getItems().get(getIndex());
                ManageCropModel.getInstance().remove(crop);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(deleteBtn);
            }
        }
    });

        
        
    }    
    
}
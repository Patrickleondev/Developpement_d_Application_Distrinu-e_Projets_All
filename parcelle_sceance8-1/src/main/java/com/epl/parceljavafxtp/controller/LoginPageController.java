/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.epl.parceljavafxtp.controller;

import com.epl.parceljavafxtp.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author BELL
 */
public class LoginPageController implements Initializable {

    
    @FXML
    private TextField emailTF;

    @FXML
    private Button loginBtn;
    
    @FXML
    private Text loginLabel;

    @FXML
    private PasswordField passwordTF;

    @FXML
    void handleLoginBtn(ActionEvent event) throws IOException {
        App.setRoot("homePage");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

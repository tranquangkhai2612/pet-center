/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers;

import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Trần Quang Khải
 */
public class LoginController implements Initializable {

    @FXML
    public Button eLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eLogin.setOnAction(actionEvent -> onLogin());
    }    
    
    private void onLogin(){
        Stage stage = (Stage) eLogin.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showAdminWindow();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers;

import com.group4.petcenter.Models.Enums.AccountTypeEnum;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Trần Quang Khải
 */
public class LoginController implements Initializable {
    @FXML
    public ChoiceBox<AccountTypeEnum> eAccountSelect;

    @FXML
    public Label eError;

    @FXML
    public Button eLogin;

    @FXML
    public TextField ePassword;

    @FXML
    public TextField eUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eAccountSelect.setItems(FXCollections.observableArrayList(AccountTypeEnum.ADMIN, AccountTypeEnum.DOCTOR, AccountTypeEnum.SALERS));
        eAccountSelect.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        eAccountSelect.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(eAccountSelect.getValue()));
        eLogin.setOnAction(actionEvent -> onLogin());
    }    
    
    private void onLogin(){
        Stage stage = (Stage) eError.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountTypeEnum.ADMIN){
            Model.getInstance().getViewFactory().showAdminWindow();
        }if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountTypeEnum.DOCTOR){
            Model.getInstance().getViewFactory().showDoctorWindow();
        }
        else{
            Model.getInstance().getViewFactory().showSalerWindow();
        }
    }
    
    
}

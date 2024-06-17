/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Models.Enums.StaffMenuEnum;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Trần Quang Khải
 */
public class StaffMenuController implements Initializable{
    @FXML
    public Button eCustomer;

    @FXML
    public Button eLogout;

    @FXML
    public Button ePayment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }
    
    private void addListeners(){
        eCustomer.setOnAction(actionEvent -> onCustomers());
        ePayment.setOnAction(actionEvent -> onPayment());
        eLogout.setOnAction(actionEvent -> onLogout());
    }

    private void onCustomers() {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuEnum.CUSTOMER);
    }
    
    private void onPayment() {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuEnum.PAYMENT);
    }
    
    private void onLogout() {
        Stage stage = (Stage) eLogout.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Models.Enums.AdminMenuEnum;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Trần Quang Khải
 */
public class AdminMenuController implements Initializable {

    @FXML
    public Button eOverview;
    @FXML
    public Button eProducts;
    @FXML
    public Button eDoctors;
    @FXML
    public Button eSalers;
    @FXML
    public Button eSettings;
    @FXML
    public Button eLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }    
    
    private void addListeners(){
        eOverview.setOnAction(actionEvent -> onOverview());
        eProducts.setOnAction(actionEvent -> onProducts());
        eDoctors.setOnAction(actionEvent -> onDoctors());
        eSalers.setOnAction(actionEvent -> onSalers());
    }

    private void onProducts() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.PRODUCTS);
    }

    private void onOverview(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.OVERVIEW);
    }
    
    private void onDoctors(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.DOCTORS);
    }
    
    private void onSalers(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.SALERS);
    }
}

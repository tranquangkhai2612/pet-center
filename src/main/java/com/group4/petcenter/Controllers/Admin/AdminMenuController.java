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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Trần Quang Khải
 */
public class AdminMenuController implements Initializable {

    @FXML
    public Button eDiagnosisServices;

    @FXML
    public Button eHealthRecords;

    @FXML
    public Button eLogout;

    @FXML
    public Button eProducts;

    @FXML
    public Button eUsers;
    
    @FXML
    private Button eDashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }    
    
    private void addListeners(){
        eUsers.setOnAction(actionEvent -> onUsers());
        eProducts.setOnAction(actionEvent -> onProducts());
        eHealthRecords.setOnAction(actionEvent -> onHealthRecords());
        eDiagnosisServices.setOnAction(actionEvent -> onDiagnosisServices());
        eLogout.setOnAction(actionEvent -> onLogout());
        eDashboard.setOnAction(e -> onDashboard());
    }

    private void onProducts() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.PRODUCTS);
    }

    private void onUsers(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.USERS);
    }
    
    private void onDashboard(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.DASHBOARD);
    }
    
    private void onHealthRecords(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.HEALTH_RECORDS);
    }
    
    private void onDiagnosisServices(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuEnum.DIAGNOSIS_SERVICES);
    }

    private void onLogout() {
        Stage stage = (Stage) eLogout.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}

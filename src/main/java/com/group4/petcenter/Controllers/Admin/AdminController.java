/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import static com.group4.petcenter.Models.Enums.AdminMenuEnum.*;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Trần Quang Khải
 */
public class AdminController implements Initializable {
    public BorderPane adminParent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            
            if(newVal.equals(USERS)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getUsersView());
            }else if(newVal.equals(DASHBOARD)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }else if(newVal.equals(HEALTH_RECORDS)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getHealthRecordsView());
            }else if(newVal.equals(DIAGNOSIS_SERVICES)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getDiagnosisServicesView());
            }else{
                adminParent.setCenter(Model.getInstance().getViewFactory().getProductsView());
            }
        }));
    }    
    
    
    
}

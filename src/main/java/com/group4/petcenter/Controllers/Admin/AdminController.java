/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import static com.group4.petcenter.Models.Enums.AdminMenuEnum.DOCTORS;
import static com.group4.petcenter.Models.Enums.AdminMenuEnum.PRODUCTS;
import static com.group4.petcenter.Models.Enums.AdminMenuEnum.SALERS;
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
            
            if(newVal.equals(PRODUCTS)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getProductsView());
            }else if(newVal.equals(DOCTORS)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getDoctorsView());
            }else if(newVal.equals(SALERS)){
                adminParent.setCenter(Model.getInstance().getViewFactory().getSalersView());
            }else{
                adminParent.setCenter(Model.getInstance().getViewFactory().getOverviewView());
            }
        }));
    }    
    
    
    
}

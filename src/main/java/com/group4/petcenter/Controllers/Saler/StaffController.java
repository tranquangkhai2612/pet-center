/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Saler;

import static com.group4.petcenter.Models.Enums.StaffMenuEnum.CUSTOMER;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Trần Quang Khải
 */
public class StaffController implements Initializable{
    public BorderPane staffParent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            
            if(newVal.equals(CUSTOMER)){
                staffParent.setCenter(Model.getInstance().getViewFactory().getCustomersView());
            }else{
                staffParent.setCenter(Model.getInstance().getViewFactory().getPaymentView());
            }
        }));
    }
}

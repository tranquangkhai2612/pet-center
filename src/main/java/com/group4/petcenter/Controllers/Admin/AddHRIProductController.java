/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Controllers.AlertMessage;
import com.group4.petcenter.Models.HealthRecord;
import com.group4.petcenter.Models.Model;
import com.group4.petcenter.Models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 *
 * @author Trần Quang Khải
 */
public class AddHRIProductController implements Initializable{
    @FXML
    public Button eCreateBtn;

    @FXML
    public Label eHrId;

    @FXML
    public Label eId;

    @FXML
    public Label eProIdLabel;

    @FXML
    public Label eProSelLabel;

    @FXML
    public TextField eProductId;

    @FXML
    public ComboBox<Product> eProductList;

    @FXML
    public TextField eQuantity;

    @FXML
    public Label eSerIdLabel1;
    
    private HealthRecord healthRecord;
    private String id;
    private AlertMessage alert = new AlertMessage();

    public AddHRIProductController(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eHrId.setText(healthRecord.getHealthRecordId());
        id = "HRI" + Model.getInstance().getDB().getCurrentDateTimeString();
        eId.setText(id);
        ObservableList<Product> list = Model.getInstance().getDB().getProducts();
        eProductList.setItems(list);
        eProductList.setValue(list.get(0));
        eProductId.setText(list.get(0).getProductsId());
        updateIdOnSelect();
        
        eQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // If the newValue contains non-digit characters, revert to the oldValue
                eQuantity.setText(oldValue);
                alert.errorMessage("Input must be an integer.");
            }
        });
        
        eCreateBtn.setOnAction(e -> {
            Product product = eProductList.getSelectionModel().getSelectedItem();
            String q = eQuantity.getText();
            if(!q.isEmpty()){
                int quantity = Integer.parseInt(q);
                Model.getInstance().getDB().createHRIProduct(id, healthRecord.getHealthRecordId(), product.getProductsId(), quantity);
                close();
                Model.getInstance().getViewFactory().refreshHealthRecordItems();
            }else{
                alert.errorMessage("Empty quantity");
            }
        });
    }
    
    private void updateIdOnSelect(){
        eProductList.setOnAction(e -> {
            Product p = eProductList.getSelectionModel().getSelectedItem();
            eProductId.setText(p.getProductsId());
        });
    }
    
    private void close(){
        Stage stage  = (Stage) eCreateBtn.getScene().getWindow();
        stage.close();
    }
}

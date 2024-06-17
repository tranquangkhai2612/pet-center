/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Controllers.AlertMessage;
import com.group4.petcenter.Models.HealthRecord;
import com.group4.petcenter.Models.Model;
import com.group4.petcenter.Models.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Trần Quang Khải
 */
public class AddHRIServiceController implements Initializable{
    @FXML
    public Button eCreateBtn;

    @FXML
    public Label eHrId;

    @FXML
    public Label eId;

    @FXML
    public TextField eQuantity;

    @FXML
    public Label eSerIdLabel;

    @FXML
    public Label eSerIdLabel1;

    @FXML
    public Label eSerSelLabel;

    @FXML
    public TextField eServiceId;

    @FXML
    public ComboBox<Service> eServiceList;
    
    private HealthRecord healthRecord;
    private String id;
    private AlertMessage alert = new AlertMessage();
    
    public AddHRIServiceController(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eHrId.setText(healthRecord.getHealthRecordId());
        id = "HRI" + Model.getInstance().getDB().getCurrentDateTimeString();
        eId.setText(id);
        ObservableList<Service> list = Model.getInstance().getDB().getServices();
        eServiceList.setItems(list);
        eServiceList.setValue(list.get(0));
        eServiceId.setText(list.get(0).getServiceId());
        updateIdOnSelect();
        
        eQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // If the newValue contains non-digit characters, revert to the oldValue
                eQuantity.setText(oldValue);
                alert.errorMessage("Input must be an integer.");
            }
        });
        
        eCreateBtn.setOnAction(e -> {
            Service service = eServiceList.getSelectionModel().getSelectedItem();
            String q = eQuantity.getText();
            if(!q.isEmpty()){
                int quantity = Integer.parseInt(q);
                Model.getInstance().getDB().createHRIService(id, healthRecord.getHealthRecordId(), service.getServiceId(), quantity);
                close();
                Model.getInstance().getViewFactory().refreshHealthRecordItems();
            }else{
                alert.errorMessage("Empty quantity");
            }
        });
    }
    
    private void updateIdOnSelect(){
        eServiceList.setOnAction(e -> {
            Service s = eServiceList.getSelectionModel().getSelectedItem();
            eServiceId.setText(s.getServiceId());
        });
    }
    
    private void close(){
        Stage stage  = (Stage) eCreateBtn.getScene().getWindow();
        stage.close();
    }
}

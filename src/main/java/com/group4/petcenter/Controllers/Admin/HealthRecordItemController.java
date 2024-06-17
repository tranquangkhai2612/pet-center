package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Models.HealthRecordItem;
import com.group4.petcenter.Models.Model;
import com.group4.petcenter.Models.Product;
import com.group4.petcenter.Models.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HealthRecordItemController implements Initializable{

    @FXML
    public Button eDeleteBtn;

    @FXML
    public Button eEditBtn;

    @FXML
    public Label eHrId;

    @FXML
    public Label eId;

    @FXML
    public TextField eProductId;

    @FXML
    public TextField eServiceId;

    @FXML
    public ComboBox<Product> eProductList;

    @FXML
    public ComboBox<Service> eServiceList;

    @FXML
    public Button eUpdateBtn;
    
    @FXML
    public Label eProIdLabel;

    @FXML
    public Label eProSelLabel;
    
    @FXML
    public Label eSerIdLabel;

    @FXML
    public Label eSerSelLabel;
    
    @FXML
    public TextField eQuantity;
    
    private HealthRecordItem healthRecordItem;

    public HealthRecordItemController(HealthRecordItem healthRecordItem) {
        this.healthRecordItem = healthRecordItem;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eId.setText(healthRecordItem.getHealthRecordItemId());
        eHrId.setText(healthRecordItem.getHealthRecordId());
        eQuantity.setText(String.valueOf(healthRecordItem.getHriQuantity()));
        eServiceId.setEditable(false);
        eProductId.setEditable(false);
        setEditable(false);
        updateIdOnSelect();
        
        if(!healthRecordItem.isIsProduct()){
            setupGUIService();
            eServiceId.setText(healthRecordItem.getServiceId());
            eServiceList.setItems(Model.getInstance().getDB().getServices());
            eServiceList.setValue(healthRecordItem.getService());
        }else{
            setupGUIProduct();
            eProductId.setText(healthRecordItem.getProductsId());
            eProductList.setItems(Model.getInstance().getDB().getProducts());
            eProductList.setValue(healthRecordItem.getProduct());
        }
        addListeners();
    }
    
    private void setEditable(boolean editable){
        if(editable == true){
            eQuantity.setEditable(editable);
            eProductList.setEditable(editable);
            eServiceList.setEditable(editable);
        }else{
            eQuantity.setEditable(editable);
            eProductList.getEditor().setEditable(editable);
            eServiceList.getEditor().setEditable(editable);
        }
    }
    
    private void setupGUIService(){
        eProductId.setVisible(false);
        eProIdLabel.setVisible(false);
        eProductList.setVisible(false);
        eProSelLabel.setVisible(false);
        
        eUpdateBtn.setVisible(false);
        eServiceId.setEditable(false);
        eServiceList.setEditable(false);
    }
    
    private void setupGUIProduct(){
        eServiceList.setVisible(false);
        eServiceId.setVisible(false);
        eSerIdLabel.setVisible(false);
        eSerSelLabel.setVisible(false);
        
        eUpdateBtn.setVisible(false);
        eServiceId.setEditable(false);
        eServiceList.setEditable(false);
    }
    
    private void addListeners(){
        eEditBtn.setOnAction(e -> {
            eUpdateBtn.setVisible(true);
            eEditBtn.setVisible(false);
            setEditable(true);
        });
        eUpdateBtn.setOnAction(e -> {
            eUpdateBtn.setVisible(false);
            eEditBtn.setVisible(true);
            
            String pId = "";
            String sId = "";
            if(healthRecordItem.isIsProduct()){
                pId = eProductId.getText();
            }else{
                sId = eServiceId.getText();
            }
            if(eQuantity.getText().isEmpty()){
                Model.getInstance().getAlertMessage().errorMessage("Empty quantity!");
                return;
            }
            int quantity = Integer.parseInt(eQuantity.getText());
            
            Model.getInstance().getDB().updateHealthRecordItem(healthRecordItem.getHealthRecordItemId(), 
                    pId, 
                    sId, 
                    quantity);
            Model.getInstance().getViewFactory().refreshHealthRecordItems();
            close();
        });
        eDeleteBtn.setOnAction(e -> {
            delete();
        });
        
        eQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // If the newValue contains non-digit characters, revert to the oldValue
                eQuantity.setText(oldValue);
                Model.getInstance().getAlertMessage().errorMessage("Input must be an integer.");
            }
        });
    }
    
    private void delete(){
        if(Model.getInstance().getAlertMessage().confirmationMessage("Are you sure to delete this health record item?")){
            Model.getInstance().getDB().deleteHealthRecordItem(healthRecordItem.getHealthRecordItemId());
            Model.getInstance().getViewFactory().refreshHealthRecordItems();
            close();
        }
    }
    
    private void close(){
        Stage stage  = (Stage) eDeleteBtn.getScene().getWindow();
        stage.close();
    }
    
    private void updateIdOnSelect(){
        eProductList.setOnAction(e -> {
            Product p = eProductList.getSelectionModel().getSelectedItem();
            eProductId.setText(p.getProductsId());
        });
        eServiceList.setOnAction(e -> {
            Service s = eServiceList.getSelectionModel().getSelectedItem();
            eServiceId.setText(s.getServiceId());
        });
    }
}

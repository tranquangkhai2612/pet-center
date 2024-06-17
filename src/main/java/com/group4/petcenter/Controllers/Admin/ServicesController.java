/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Models.Model;
import com.group4.petcenter.Models.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Trần Quang Khải
 */
public class ServicesController implements Initializable{
    @FXML
    public Button e_btn_cancel;

    @FXML
    public Button e_btn_create;

    @FXML
    public Button e_btn_create_new;

    @FXML
    public Button e_btn_delete;

    @FXML
    public Button e_btn_edit;

    @FXML
    public Button e_btn_update;

    @FXML
    public TableColumn<Service, String> e_col_id;

    @FXML
    public TableColumn<Service, String> e_col_name;

    @FXML
    public TableColumn<Service, Double> e_col_price;

    @FXML
    public TextField e_id;

    @FXML
    public TextField e_name;

    @FXML
    public TextField e_price;

    @FXML
    public TableView<Service> e_table_services;
    
    private Service selectedService = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showServices();
        addListeners();
        onCancel();
    }

    private void showServices() {
        ObservableList<Service> list = Model.getInstance().getDB().getServices();
        e_col_id.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        e_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        e_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        e_table_services.setItems(list);
    }
    
    private void addListeners(){
        e_table_services.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedService = e_table_services.getSelectionModel().getSelectedItem();
                onSelectedService();
                e_table_services.getSelectionModel().clearSelection();
            }
        });
        
        Model.getInstance().getViewFactory().addDoubleValidation(e_price);
        
        e_btn_create_new.setOnAction(e -> {
            onCreateNew();
        });
        
        e_btn_cancel.setOnAction(e -> {
            onCancel();
        });
        
        e_btn_edit.setOnAction(e -> {
            onEdit();
        });
        
        e_btn_create.setOnAction(e -> {
            onCreate();
        });
        
        e_btn_update.setOnAction(e -> {
            onUpdate();
        });
        
        e_btn_delete.setOnAction(e -> {
            onDelete();
        });
    }
    
    private void onSelectedService(){
        e_id.setText(selectedService.getServiceId());
        e_name.setText(selectedService.getName());
        e_price.setText(selectedService.getPrice().toString());
        
        onSelected();
    }
    
    private void onSelected(){
        e_btn_create.setVisible(false);
        e_btn_update.setVisible(false);
        e_btn_create_new.setVisible(true);
        e_btn_edit.setVisible(true);
        e_btn_cancel.setVisible(true);
        e_btn_delete.setVisible(true);
        
        e_id.setEditable(false);
        e_name.setEditable(false);
        e_price.setEditable(false);
    }
    
    private void onEdit(){
        e_id.setEditable(false);
        e_name.setEditable(true);
        e_price.setEditable(true);
        
        e_btn_update.setVisible(true);
        e_btn_cancel.setVisible(true);
        e_btn_edit.setVisible(false);
        e_btn_create_new.setVisible(false);
        e_btn_create.setVisible(false);
        e_btn_delete.setVisible(false);
    }
    
    private void onCancel(){
        e_id.setText("");
        e_name.setText("");
        e_price.setText("");
        selectedService = null;
        
        e_btn_create_new.setVisible(true);
        e_btn_create.setVisible(false);
        e_btn_edit.setVisible(false);
        e_btn_update.setVisible(false);
        e_btn_cancel.setVisible(false);
        e_btn_delete.setVisible(false);
        showServices();
    }
    
    private void onCreateNew(){
        String id = "S" + Model.getInstance().getDB().getCurrentDateTimeString();
        
        e_id.setText(id);
        e_name.setText("");
        e_price.setText("");
        
        selectedService = null;
        e_id.setEditable(false);
        e_name.setEditable(true);
        e_price.setEditable(true);
        
        e_btn_create_new.setVisible(false);
        e_btn_create.setVisible(true);
        e_btn_edit.setVisible(false);
        e_btn_update.setVisible(false);
        e_btn_cancel.setVisible(true);
        e_btn_delete.setVisible(false);
    }
    
    private void onUpdate(){
        String id = e_id.getText();
        String name = e_name.getText();
        if(name.isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty name!");
            return;
        }
        if(e_price.getText().isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty price!");
            return;
        }
        double price = Double.valueOf(e_price.getText());
       
        Service s = new Service(id, price, name);
        Model.getInstance().getDB().updateService(s);
        
        onCancel();   
    }
    
    private void onDelete(){
        Model.getInstance().getDB().deleteService(selectedService.getServiceId());
        onCancel();
    }
    
    private void onCreate(){
        String id = e_id.getText();
        String name = e_name.getText();
        if(name.isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty name!");
            return;
        }
        if(e_price.getText().isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty price!");
            return;
        }
        double price = Double.valueOf(e_price.getText());
       
        Service s = new Service(id, price, name);
        Model.getInstance().getDB().createService(s);
        
        onCancel();
    }
}

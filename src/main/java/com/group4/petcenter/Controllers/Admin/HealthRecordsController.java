/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Controllers.Saler.Customer;
import com.group4.petcenter.Models.Animal;
import com.group4.petcenter.Models.HealthRecord;
import com.group4.petcenter.Models.HealthRecordItem;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author Trần Quang Khải
 */
public class HealthRecordsController implements Initializable{
    @FXML
    public Button eAddProductBtn;

    @FXML
    public Button eAddServiceBtn;

    @FXML
    public Text eAnimalId;

    @FXML
    public TableColumn<HealthRecord, Void> eColHRAction;

    @FXML
    public TableColumn<HealthRecord, StringProperty> eColHRAnimalId;

    @FXML
    public TableColumn<HealthRecord, ObjectProperty> eColHRDate;

    @FXML
    public TableColumn<HealthRecord, StringProperty> eColHRDianosis;

    @FXML
    public TableColumn<HealthRecord, StringProperty> eColHRId;
    
    @FXML
    public TableColumn<HealthRecord, StringProperty> eColHRUserId;

    @FXML
    public TableColumn<HealthRecordItem, Void> eColHRItemAction;

    @FXML
    public TableColumn<HealthRecordItem, StringProperty> eColHRItemItems;

    @FXML
    public TableColumn<HealthRecordItem, IntegerProperty> eColHRItemQuantity;
    
    @FXML
    public TextField eSearchInput;

    @FXML
    public Text eSelectedHRId;
    
    @FXML
    public TableView<HealthRecord> eTableHR;
    
    @FXML
    public TableView<HealthRecordItem> eTableHRI;
    
    @FXML
    public TableColumn<Animal, String> e_animal_col_name;

    @FXML
    public TableColumn<Animal, Void> e_col_animal_action;

    @FXML
    public TableColumn<Animal, String> e_col_animal_birth;

    @FXML
    public TableColumn<Animal, String> e_col_animal_breed;

    @FXML
    public TableColumn<Animal, String> e_col_animal_gender;

    @FXML
    public TableColumn<Animal, String> e_col_animal_owner;
    
    @FXML 
    public TableView<Animal> eTableAnimal;
    
    private HealthRecord selectedHealthRecord;
    private HealthRecordItem selectedHealthRecordItem;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearDetails();
        showHealthRecords();
        addListeners();
    }
    
    private void addListeners(){
        eTableHR.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedHealthRecord = eTableHR.getSelectionModel().getSelectedItem();
                onSelectedHealthRecord();
                eTableHR.getSelectionModel().clearSelection();
            }
        });
        eAddProductBtn.setOnAction(e -> {
            Model.getInstance().getViewFactory().showHRIProductWindow(selectedHealthRecord);
        });
        eAddServiceBtn.setOnAction(e -> {
            Model.getInstance().getViewFactory().showHRIServiceWindow(selectedHealthRecord);
        });
    }
    
    private void onSelectedHealthRecord(){
        eAnimalId.setText(selectedHealthRecord.getAnimalId());
        eSelectedHRId.setText(selectedHealthRecord.getHealthRecordId());
        
        showAnimal(selectedHealthRecord.getAnimalId());
        showHealthRecordItems();
    }
    
    private void clearDetails(){
        eAnimalId.setText("");
        eSelectedHRId.setText("");
    }
    
    public ObservableList<HealthRecord> getHealthRecords(){
        ObservableList<HealthRecord> healthRecords = FXCollections.observableArrayList();
        ResultSet resultSet = Model.getInstance().getDB().getHealthRecordsData();
        HealthRecord healthRecord = null;
        
        try {
            while(resultSet.next()){
                String id = resultSet.getString("Health_record_id");
                String userId = resultSet.getString("User_id");
                String animalId = resultSet.getString("Animal_id");
                String[] dateParts = resultSet.getString("Record_date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String dianosis = resultSet.getString("Diagnosis");
                String symptoms = resultSet.getString("Symptoms");

                healthRecord = new HealthRecord(id, userId, animalId, date, dianosis, symptoms);
                healthRecords.add(healthRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return healthRecords;
    }
    
    public void showHealthRecords(){
        ObservableList<HealthRecord> list = getHealthRecords();
        eColHRId.setCellValueFactory(new PropertyValueFactory<>("healthRecordId"));
        eColHRUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        eColHRAnimalId.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        eColHRDate.setCellValueFactory(new PropertyValueFactory<>("recordDate"));
        eColHRDianosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        Callback<TableColumn<HealthRecord, Void>, TableCell<HealthRecord, Void>> cellFactory = new Callback<TableColumn<HealthRecord, Void>, TableCell<HealthRecord, Void>>() {
            @Override
            public TableCell<HealthRecord, Void> call(final TableColumn<HealthRecord, Void> param) {
                final TableCell<HealthRecord, Void> cell = new TableCell<HealthRecord, Void>() {

                    private final Button btn = new Button("Detail");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedHealthRecord = eTableHR.getItems().get(getIndex());
                            Model.getInstance().getViewFactory().selectedHealthRecordId = selectedHealthRecord.getHealthRecordId();
                            Model.getInstance().getViewFactory().showHealthRecordWindow();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        eColHRAction.setCellFactory(cellFactory);
        eTableHR.setItems(list);
    }
    
    public void showHealthRecordItems(){
        ObservableList<HealthRecordItem> list = selectedHealthRecord.getHealthRecordItems();
        eColHRItemItems.setCellValueFactory(new PropertyValueFactory<>("healthRecordItemName"));
        eColHRItemQuantity.setCellValueFactory(new PropertyValueFactory<>("hriQuantity"));
        Callback<TableColumn<HealthRecordItem, Void>, TableCell<HealthRecordItem, Void>> cellFactory = new Callback<TableColumn<HealthRecordItem, Void>, TableCell<HealthRecordItem, Void>>() {
            @Override
            public TableCell<HealthRecordItem, Void> call(final TableColumn<HealthRecordItem, Void> param) {
                final TableCell<HealthRecordItem, Void> cell = new TableCell<HealthRecordItem, Void>() {

                    private final Button btn = new Button("Detail");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedHealthRecordItem = eTableHRI.getItems().get(getIndex());
                            Model.getInstance().getViewFactory().showHealthRecordItemWindow(selectedHealthRecordItem);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
       
        eColHRItemAction.setCellFactory(cellFactory);
        eTableHRI.setItems(list);
    }
    
    public void showAnimal(String animalId){
        ObservableList<Animal> list = FXCollections.observableArrayList();
        Animal animal = Model.getInstance().getDB().getAnimalById(animalId);
        list.add(animal);
        e_animal_col_name.setCellValueFactory(new PropertyValueFactory<>("animalName"));
        e_col_animal_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        e_col_animal_birth.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        e_col_animal_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        e_col_animal_owner.setCellValueFactory(new PropertyValueFactory<>("customerNameProperty"));
        Callback<TableColumn<Animal, Void>, TableCell<Animal, Void>> cellFactory = new Callback<TableColumn<Animal, Void>, TableCell<Animal, Void>>() {
            @Override
            public TableCell<Animal, Void> call(final TableColumn<Animal, Void> param) {
                final TableCell<Animal, Void> cell = new TableCell<Animal, Void>() {

                    private final Button btn = new Button("Edit");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            selectedHealthRecord = eTableHR.getItems().get(getIndex());
                            Model.getInstance().getViewFactory().showAnimalWindow(animal);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
       
        e_col_animal_action.setCellFactory(cellFactory);
        eTableAnimal.setItems(list);
    }
}

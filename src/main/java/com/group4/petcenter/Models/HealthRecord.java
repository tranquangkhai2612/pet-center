/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Trần Quang Khải
 */
public class HealthRecord {
    private final StringProperty healthRecordId;
    private final StringProperty userId;
    private final StringProperty animalId;
    private final ObjectProperty<LocalDate> recordDate;
    private final StringProperty diagnosis;
    private final StringProperty symptoms;
    private final ObservableList<HealthRecordItem> healthRecordItems;
    private Animal animal;

    public HealthRecord(String healthRecordId, String userId, String animalId, LocalDate recordDate, String diagnosis, String symptoms) {
        this.healthRecordId = new SimpleStringProperty(this,"Health Record Id",healthRecordId);
        this.userId = new SimpleStringProperty(this,"User Id",userId);
        this.animalId = new SimpleStringProperty(this,"Animal Id",animalId);
        this.recordDate = new SimpleObjectProperty<>(this,"Record Date",recordDate);
        this.diagnosis = new SimpleStringProperty(this,"Dianosis",diagnosis);
        this.symptoms = new SimpleStringProperty(this,"Symptoms",symptoms);
        
        this.healthRecordItems = getHealthRecordItems();
        this.animal = Model.getInstance().getDB().getAnimalById(this.animalId.get());
    }
    
    public ObservableList<HealthRecordItem> getHealthRecordItems(){
        ObservableList<HealthRecordItem> healthRecordItems = FXCollections.observableArrayList();
        ResultSet resultSet = Model.getInstance().getDB().getHealthRecordItems(healthRecordId.get());
        HealthRecordItem healthRecordItem = null;
        
        try {
            while(resultSet.next()){
                String id = resultSet.getString("Health_record_item_id");
                String productId = resultSet.getString("Products_id");
                if(productId == null){
                    productId = "";
                }
                String serviceId = resultSet.getString("Service_id");
                if(serviceId == null){
                    serviceId = "";
                }
                int quantity = resultSet.getInt("Quantity");
                healthRecordItem = new HealthRecordItem(id, healthRecordId.get(), productId, serviceId, quantity);
                healthRecordItems.add(healthRecordItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return healthRecordItems;
    }

    public String getHealthRecordId() {
        return healthRecordId.get();
    }

    public void setHealthRecordId(String healthRecordId) {
        this.healthRecordId.set(healthRecordId);
    }

    public StringProperty healthRecordIdProperty() {
        return healthRecordId;
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public StringProperty userIdProperty() {
        return userId;
    }

    public String getAnimalId() {
        return animalId.get();
    }

    public void setAnimalId(String animalId) {
        this.animalId.set(animalId);
    }

    public StringProperty animalIdProperty() {
        return animalId;
    }

    public LocalDate getRecordDate() {
        return recordDate.get();
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate.set(recordDate);
    }

    public ObjectProperty<LocalDate> recordDateProperty() {
        return recordDate;
    }

    public String getDiagnosis() {
        return diagnosis.get();
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis.set(diagnosis);
    }

    public StringProperty diagnosisProperty() {
        return diagnosis;
    }

    public String getSymptoms() {
        return symptoms.get();
    }

    public void setSymptoms(String symptoms) {
        this.symptoms.set(symptoms);
    }

    public StringProperty symptomsProperty() {
        return symptoms;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}

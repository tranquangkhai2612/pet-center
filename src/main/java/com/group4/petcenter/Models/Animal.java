/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Models;

import com.group4.petcenter.Controllers.Saler.Customer;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Trần Quang Khải
 */
public class Animal {
    private final StringProperty animalId;
    private final StringProperty animalName;
    private final StringProperty gender;
    private final ObjectProperty<LocalDate> birthdate;
    private final StringProperty breed;
    private final StringProperty customerId;
    private final StringProperty customerNameProperty;
    private Customer customer;

    public Animal() {
        this.animalId = new SimpleStringProperty();
        this.animalName = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.birthdate = new SimpleObjectProperty<>();
        this.breed = new SimpleStringProperty();
        this.customerId = new SimpleStringProperty();
        this.customerNameProperty = new SimpleStringProperty();
    }

    public Animal(String animalId, String animalName, String gender, LocalDate birthdate, String breed, String customerId) {
        this.animalId = new SimpleStringProperty(animalId);
        this.animalName = new SimpleStringProperty(animalName);
        this.gender = new SimpleStringProperty(gender);
        this.birthdate = new SimpleObjectProperty<>(birthdate);
        this.breed = new SimpleStringProperty(breed);
        this.customerId = new SimpleStringProperty(customerId);
        this.customer = Model.getInstance().getDB().getCustomerById(this.customerId.get());
        this.customerNameProperty = new SimpleStringProperty(this, "Customer Name", customer.getCustomerName());
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

    public String getAnimalName() {
        return animalName.get();
    }

    public void setAnimalName(String animalName) {
        this.animalName.set(animalName);
    }

    public StringProperty animalNameProperty() {
        return animalName;
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate.set(birthdate);
    }

    public ObjectProperty<LocalDate> birthdateProperty() {
        return birthdate;
    }

    public String getBreed() {
        return breed.get();
    }

    public void setBreed(String breed) {
        this.breed.set(breed);
    }

    public StringProperty breedProperty() {
        return breed;
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public StringProperty customerIdProperty() {
        return customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerNameProperty() {
        return customerNameProperty.get();
    }
    
    public String getCustomerName() {
        return customerNameProperty.get();
    }
}

package com.group4.petcenter.Controllers.Saler;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private String customerId;
    private String customerName;
    private StringProperty nameProperty;
    private String gender;
    private String address;
    private String phone;

    public Customer(String customerId, String customerName, String gender, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.nameProperty = new SimpleStringProperty(this, "Customer Name", this.customerName);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(StringProperty nameProperty) {
        this.nameProperty = nameProperty;
    }
    
    public String getNameString(){
        return nameProperty.get();
    }
}

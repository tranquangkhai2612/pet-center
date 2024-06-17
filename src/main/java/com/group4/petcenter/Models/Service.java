/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Models;

/**
 *
 * @author Trần Quang Khải
 */
import java.math.BigDecimal;
import javafx.beans.property.*;

public class Service {
    private final StringProperty serviceId;
    private final DoubleProperty price;
    private final StringProperty name;

    public Service() {
        this.serviceId = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.name = new SimpleStringProperty();
    }

    public Service(String serviceId, double price, String name) {
        this.serviceId = new SimpleStringProperty(serviceId);
        this.price = new SimpleDoubleProperty(price);
        this.name = new SimpleStringProperty(name);
    }

    public String getServiceId() {
        return serviceId.get();
    }

    public void setServiceId(String serviceId) {
        this.serviceId.set(serviceId);
    }

    public StringProperty serviceIdProperty() {
        return serviceId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public Double getPrice() {
        return price.get();
    }
}


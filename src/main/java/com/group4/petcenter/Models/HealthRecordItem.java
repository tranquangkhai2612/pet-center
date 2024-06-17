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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HealthRecordItem {
    private final StringProperty healthRecordItemId;
    private final StringProperty healthRecordId;
    private StringProperty productsId;
    private StringProperty serviceId;
    private IntegerProperty hriQuantity;
    private StringProperty healthRecordItemName;
    private Product product;
    private Service service;
    private boolean isProduct = false;

    // Parameterized constructor
    public HealthRecordItem(String healthRecordItemId, String healthRecordId, String productsId, String serviceId, int quantity) {
        this.healthRecordItemId = new SimpleStringProperty(healthRecordItemId);
        this.healthRecordId = new SimpleStringProperty(healthRecordId);
        this.healthRecordItemName = new SimpleStringProperty();
        this.hriQuantity = new SimpleIntegerProperty(quantity);
        if(!productsId.isEmpty()){
            this.productsId = new SimpleStringProperty(productsId);
            this.isProduct = true;
            product = getProduct(productsId);
            healthRecordItemName.setValue(product.getName());
            service = null;
        }else{
            this.serviceId = new SimpleStringProperty(serviceId);
            product = null;
            service = getService(serviceId);
            healthRecordItemName.setValue(service.getName());
        }
    }

    // Getter and Setter for healthRecordItemId
    public String getHealthRecordItemId() {
        return healthRecordItemId.get();
    }

    public void setHealthRecordItemId(String healthRecordItemId) {
        this.healthRecordItemId.set(healthRecordItemId);
    }

    public StringProperty healthRecordItemIdProperty() {
        return healthRecordItemId;
    }

    // Getter and Setter for healthRecordId
    public String getHealthRecordId() {
        return healthRecordId.get();
    }

    public void setHealthRecordId(String healthRecordId) {
        this.healthRecordId.set(healthRecordId);
    }

    public StringProperty healthRecordIdProperty() {
        return healthRecordId;
    }

    // Getter and Setter for productsId
    public String getProductsId() {
        if(productsId == null){
            return "";
        }
        return productsId.get();
    }

    public void setProductsId(String productsId) {
        this.productsId.set(productsId);
    }

    public StringProperty productsIdProperty() {
        return productsId;
    }

    // Getter and Setter for serviceId
    public String getServiceId() {
        if(serviceId == null){
            return "";
        }
        
        return serviceId.get();
    }

    public void setServiceId(String serviceId) {
        this.serviceId.set(serviceId);
    }

    public StringProperty serviceIdProperty() {
        return serviceId;
    }

    public Product getProduct(String productsId) {
        ResultSet resultSet = Model.getInstance().getDB().getProduct(productsId);
        Product product = null;
        
        try {
            while(resultSet.next()){
                String id = resultSet.getString("Products_id");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                String[] dateParts = resultSet.getString("Expired_date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("Price"));

                product = new Product(id, name, quantity, date, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return product;
    }

    public Service getService(String serviceId) {
        ResultSet resultSet = Model.getInstance().getDB().getService(serviceId);
        Service service = null;
        
        try {
            while(resultSet.next()){
                String id = resultSet.getString("Service_id");
                double price = resultSet.getDouble("Price");
                String name = resultSet.getString("Name");
                

                service = new Service(id, price, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return service;
    }

    public String getHealthRecordItemName() {
        return healthRecordItemName.get();
    }

    public void setHealthRecordItemName(StringProperty healthRecordItemName) {
        this.healthRecordItemName = healthRecordItemName;
    }

    public int getHriQuantity() {
        return hriQuantity.get();
    }

    public void setHriQuantity(IntegerProperty hriQuantity) {
        this.hriQuantity = hriQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public boolean isIsProduct() {
        return isProduct;
    }

    public void setIsProduct(boolean isProduct) {
        this.isProduct = isProduct;
    }

    
    
}


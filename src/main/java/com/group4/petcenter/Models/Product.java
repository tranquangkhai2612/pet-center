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

import java.time.LocalDate;

public class Product {
    private final StringProperty productsId;
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final ObjectProperty<LocalDate> expiredDate;
    private final ObjectProperty<BigDecimal> price;

    public Product() {
        this.productsId = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.expiredDate = new SimpleObjectProperty<>();
        this.price = new SimpleObjectProperty<>();
    }

    public Product(String productsId, String name, int quantity, LocalDate expiredDate, BigDecimal price) {
        this.productsId = new SimpleStringProperty(productsId);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.expiredDate = new SimpleObjectProperty<>(expiredDate);
        this.price = new SimpleObjectProperty<>(price);
    }

    public String getProductsId() {
        return productsId.get();
    }

    public void setProductsId(String productsId) {
        this.productsId.set(productsId);
    }

    public StringProperty productsIdProperty() {
        return productsId;
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

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public LocalDate getExpiredDate() {
        return expiredDate.get();
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate.set(expiredDate);
    }

    public ObjectProperty<LocalDate> expiredDateProperty() {
        return expiredDate;
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return price;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}


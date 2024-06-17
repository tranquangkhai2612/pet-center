
package com.group4.petcenter.Controllers.Saler;
import com.group4.petcenter.Controllers.Saler.Billdetail;
import java.util.List;
import javafx.beans.property.*;

public class Product {
    private StringProperty productId;
    private StringProperty name;
    private IntegerProperty quantity;
    private StringProperty expiredDate;
    private IntegerProperty price; // Thay đổi từ BigDecimal sang IntegerProperty
    private List<Billdetail> billDetailList; // Thay đổi tên biến cho rõ ràng

    public Product(String productId, String name, int quantity, String expiredDate, int price) {
        this.productId = new SimpleStringProperty(productId);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.expiredDate = new SimpleStringProperty(expiredDate);
        this.price = new SimpleIntegerProperty(price);
    }

    // Các phương thức getter và setter sử dụng JavaFX properties
    public String getProductId() {
        return productId.get();
    }

    public StringProperty productIdProperty() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId.set(productId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getExpiredDate() {
        return expiredDate.get();
    }

    public StringProperty expiredDateProperty() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate.set(expiredDate);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public List<Billdetail> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<Billdetail> billDetailList) {
        this.billDetailList = billDetailList;
    }
}

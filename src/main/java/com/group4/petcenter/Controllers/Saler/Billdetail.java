package com.group4.petcenter.Controllers.Saler;

import java.math.BigDecimal;

public class Billdetail {
    private int billDetailId;
    private Bill billId;
    private String productName;
    private String serviceName;
    private int quantity;
    private int quantityService;
    private BigDecimal priceService;
    private BigDecimal unitPrice;
    private BigDecimal amount;

    public Billdetail() {
    }

    public Billdetail(int billDetailId, Bill billId, String productName, String serviceName, int quantity, int quantityService, BigDecimal priceService, BigDecimal unitPrice, BigDecimal amount) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.productName = productName;
        this.serviceName = serviceName;
        this.quantity = quantity;
        this.quantityService = quantityService;
        this.priceService = priceService;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public Bill getBillId() {
        return billId;
    }

    public void setBillId(Bill billId) {
        this.billId = billId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityService() {
        return quantityService;
    }

    public void setQuantityService(int quantityService) {
        this.quantityService = quantityService;
    }

    public BigDecimal getPriceService() {
        return priceService;
    }

    public void setPriceService(BigDecimal priceService) {
        this.priceService = priceService;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

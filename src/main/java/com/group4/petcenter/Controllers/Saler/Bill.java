/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Saler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Bill {
    
    private String billId;
    private String customerId;
    private Date date;
    private String healthRecordId;
    private String note;
    private int total; // Changed to int
    private List<Billdetail> billdetailist;
    public Bill() {
    }

    public Bill(String billId, String customerId, Date date, String healthRecordId, String note, int total) { // Updated constructor parameters
        this.billId = billId;
        this.customerId = customerId;
        this.date = date;
        this.healthRecordId = healthRecordId;
        this.note = note;
        this.total = total;
    }

    public String getBillId() {
        return billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getDate() {
        return date;
    }

    public String getHealthRecordId() {
        return healthRecordId;
    }

    public String getNote() {
        return note;
    }

    public int getTotal() { // Changed return type to int
        return total;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHealthRecordId(String healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTotal(int total) { // Changed parameter type to int
        this.total = total;
    }

    public List<Billdetail> getBilldetailist() {
        return billdetailist;
    }

    public void setBilldetailist(List<Billdetail> billdetailist) {
        this.billdetailist = billdetailist;
    }
    
}

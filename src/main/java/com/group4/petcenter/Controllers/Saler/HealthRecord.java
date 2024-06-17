/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Saler;

/**
 *
 * @author LENOVO
 */
public class HealthRecord {
    private String recordId;
    private String userId;
    private String animalId;
    private String date;
    private String diagnosi;
    private String Symptoms;

    public HealthRecord() {
    }

    public HealthRecord(String recordId, String userId, String animalId, String date, String diagnosi, String Symptoms) {
        this.recordId = recordId;
        this.userId = userId;
        this.animalId = animalId;
        this.date = date;
        this.diagnosi = diagnosi;
        this.Symptoms = Symptoms;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAnimalId() {
        return animalId;
    }

    public String getDate() {
        return date;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDiagnosi(String diagnosi) {
        this.diagnosi = diagnosi;
    }

    public void setSymptoms(String Symptoms) {
        this.Symptoms = Symptoms;
    }
    
    
}

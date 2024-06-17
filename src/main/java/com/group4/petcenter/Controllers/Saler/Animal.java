package com.group4.petcenter.Controllers.Saler;

public class Animal {
    private String animalId;
    private String animalName;
    private String gender;
    private String birthdate;
    private String breed;
    private String customerId;

    public Animal(String animalId, String animalName, String gender, String birthdate, String breed, String customerId) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.breed = breed;
        this.customerId = customerId;
    }

    // Getters and settersbai

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

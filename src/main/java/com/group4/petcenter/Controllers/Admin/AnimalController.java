/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Models.Animal;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Trần Quang Khải
 */
public class AnimalController implements Initializable{

    @FXML
    public Button eEditBtn;

    @FXML
    public Label eId;

    @FXML
    public Button eUpdateBtn;

    @FXML
    public DatePicker e_birth;

    @FXML
    public TextField e_breed;

    @FXML
    public ComboBox<String> e_gender;

    @FXML
    public TextField e_name;

    @FXML
    public Label e_owner;
    
    private Animal animal;
    private ObservableList<String> genders = FXCollections.observableArrayList();

    public AnimalController(Animal animal){
        this.animal = animal;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genders.add("Male");
        genders.add("Female");
        eId.setText(animal.getAnimalId());
        e_owner.setText(animal.getCustomerName());
        e_name.setText(animal.getAnimalName());
        e_gender.setItems(genders);
        e_gender.setValue(animal.getGender());
        e_birth.setValue(animal.getBirthdate());
        e_breed.setText(animal.getBreed());
        setEditable(false);
        addListeners();
    }
    
    private void addListeners(){
        eEditBtn.setOnAction(e -> {
            setEditable(true);
        });
        
        eUpdateBtn.setOnAction(e -> {
            String name = e_name.getText();
            String breed = e_breed.getText();
            
            if(name.isEmpty() || breed.isEmpty()){
                Model.getInstance().getAlertMessage().errorMessage("Please fill all empty fields!");
            }
            
            Model.getInstance().getDB().updateAnimalById(animal.getAnimalId(), 
                    name, 
                    e_gender.getValue(), 
                    e_birth.getValue(),
                    breed);
            Model.getInstance().getViewFactory().refreshAnimal(animal.getAnimalId());
            close();
        });
    }
    
    private void setEditable(boolean editable){
        e_name.setEditable(editable);
        e_gender.setEditable(editable);
        e_birth.setEditable(editable);
        e_breed.setEditable(editable);
        if(editable){
            eUpdateBtn.setVisible(true);
            eEditBtn.setVisible(false);
        }else{
            eUpdateBtn.setVisible(false);
            eEditBtn.setVisible(true);
        }
    }
    
//    private void delete(){
//        if(Model.getInstance().getAlertMessage().confirmationMessage("Are you sure to delete this health record item?")){
//            Model.getInstance().getDB().deleteAnimalById(animal.getAnimalId());
//            Model.getInstance().getViewFactory().refreshHealthRecordItems();
//            close();
//        }
//    }
    
    private void close(){
        Stage stage  = (Stage) eId.getScene().getWindow();
        stage.close();
    }
}

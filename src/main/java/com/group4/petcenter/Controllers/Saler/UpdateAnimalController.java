package com.group4.petcenter.Controllers.Saler;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class UpdateAnimalController {
    @FXML
    public TextField tf_animal_id;
    @FXML
    public TextField tf_animal_name;
    @FXML
    public ToggleGroup tgGender;
    @FXML
    public RadioButton rb_animal_male;
    @FXML
    public RadioButton rb_animal_female;
    @FXML
    public DatePicker tf_animal_birthdate;
    @FXML
    public TextField tf_animal_breed;
    @FXML
    public TextField tf_animal_customer;
    public AnimalDAO animalDAO;
    public Animal animal;
    @FXML
    public AnchorPane mainPane;
    @FXML
    public Button btn_Update_animal;
    @FXML
    public DatePicker dp_date;
    @FXML
    public TextField tf_id_customer;

    public void initialize() {
        animalDAO = new AnimalDAO();
    }

    public void setAnimalData(Animal animal) {
        this.animal = animal;
        tf_animal_id.setText(animal.getAnimalId());
        tf_animal_name.setText(animal.getAnimalName());

        // Set gender radio button based on animal gender
        if ("male".equalsIgnoreCase(animal.getGender())) {
            rb_animal_male.setSelected(true);
        } else if ("female".equalsIgnoreCase(animal.getGender())) {
            rb_animal_female.setSelected(true);
        }

        // Parse the birthdate string to LocalDate and set it to DatePicker
        tf_animal_birthdate.setValue(LocalDate.parse(animal.getBirthdate()));
        tf_animal_breed.setText(animal.getBreed());
        tf_animal_customer.setText(animal.getCustomerId());
    }

    @FXML
    public void handleUpdateAnimalButton() {
        animal.setAnimalName(tf_animal_name.getText());

        // Get gender from selected radio button
        if (rb_animal_male.isSelected()) {
            animal.setGender("male");
        } else if (rb_animal_female.isSelected()) {
            animal.setGender("female");
        }

        // Format the date from DatePicker to string
        animal.setBirthdate(tf_animal_birthdate.getValue().toString());
        animal.setBreed(tf_animal_breed.getText());
        animal.setCustomerId(tf_animal_customer.getText());

        if (animalDAO.updateAnimal(animal)) {
            CustomerController.showAlert("Success", "Animal updated successfully.");
             switchToCustomerListPage();
        } else {
            CustomerController.showAlert("Error", "Failed to update animal.");
        }
    }
    public void switchToCustomerListPage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/Customer.fxml"));
        Parent root = loader.load();
        mainPane.getChildren().setAll(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

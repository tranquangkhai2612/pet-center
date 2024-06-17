package com.group4.petcenter.Controllers.Saler;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class AddAnimalController implements Initializable {
    public CustomerController customerController;
    public AnimalDAO animalDAO;
    public String customerId;

    @FXML
    public TextField tf_animal_id;
    @FXML
    public TextField tf_animal_name;
    @FXML
    public TextField tf_animal_breed;
    @FXML
    public DatePicker dp_date;
    @FXML
    public HBox tf_gender;
    @FXML
    public RadioButton rb_animal_male;
    @FXML
    public ToggleGroup tgGender;
    @FXML
    public RadioButton rb_animal_female;
    @FXML
    public Button btn_create_animal;
    @FXML
    public TextField tf_id_customer;
    public ObservableList<Customer> customerList;
    @FXML
    public AnchorPane mainPane;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
        tf_id_customer.setText(customerId);
         tf_id_customer.setEditable(false); // Không cho phép chỉnh sửa
    }

    public void handleCreateAnimalButton(ActionEvent event) {
        String id = tf_animal_id.getText();
        String name = tf_animal_name.getText();
        String breed = tf_animal_breed.getText();
        LocalDate birthdate = dp_date.getValue();
        String gender = rb_animal_male.isSelected() ? "Male" : "Female";
        String customerId = tf_id_customer.getText();

        if (id.isEmpty() || name.isEmpty() || breed.isEmpty() || birthdate == null || customerId.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        if (!isCustomerExists(customerId)) {
            showAlert("Error", "Customer does not exist.");
            return;
        }

        Animal newAnimal = new Animal(id, name, gender, birthdate.toString(), breed, customerId);
        boolean success = animalDAO.addAnimal(newAnimal);

        if (customerController != null) {
            customerController.setAddAnimalController(this);
        }

        if (success) {
            System.out.println("Animal added successfully!");
            openCustomerPage();
        } else {
            showAlert("Error", "Failed to add animal.");
        }
    }

    public boolean isCustomerExists(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    public void openCustomerPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/Customer.fxml"));
        try {
            Parent root = loader.load();
            CustomerController controller = loader.getController();
            Scene scene = new Scene(root);
            controller.initialize(null, null);

            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }

    public void setCustomerList(ObservableList<Customer> customerList) {
        this.customerList = customerList;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animalDAO = new AnimalDAO(); 
        btn_create_animal.setOnAction(this::handleCreateAnimalButton);
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

 

   
    
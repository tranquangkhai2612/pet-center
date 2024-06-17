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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateHRController implements Initializable {
    public String animalId;
    public HealthRecordDAO healthRecordDAO;

    @FXML
    public AnchorPane mainPane;
    @FXML
    public TextField tf_health_record_id;
    @FXML
    public TextField tf_health_record_animal_id;
    @FXML
    public Button btn_create_health_record;
    @FXML
    public DatePicker tf_health_record_date;
    @FXML
    public TextField tf_health_record_diagnosis;
    @FXML
    public TextField tf_health_record_user_id;
    @FXML
    public TextArea ta_health_record_symptoms;
    public ObservableList<Animal> animalList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        healthRecordDAO = new HealthRecordDAO();
        btn_create_health_record.setOnAction(this::handleCreateHealthRecordButton);
    }

    @FXML
    public void handleCreateHealthRecordButton(ActionEvent event) {
        String recordId = tf_health_record_id.getText();
        String userId = tf_health_record_user_id.getText();
        String animalId = tf_health_record_animal_id.getText();
        LocalDate date = tf_health_record_date.getValue();
        String diagnosis = tf_health_record_diagnosis.getText();
        String symptoms = ta_health_record_symptoms.getText();

        

        HealthRecord newHealthRecord = new HealthRecord(recordId, userId, animalId, date.toString(), diagnosis, symptoms);
        boolean success = healthRecordDAO.addHealthRecord(newHealthRecord);

        if (success) {
            showAlert("Success", "Health record added successfully!");
            openCustomerPage();
        } else {
            showAlert("Error", "Failed to add health record.");
        }
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
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
        tf_health_record_animal_id.setText(animalId);
        tf_health_record_animal_id.setEditable(false);
    }

    public void setAnimalList(ObservableList<Animal> animalList) {
        this.animalList = animalList;
    }
}

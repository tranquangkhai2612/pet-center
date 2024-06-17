package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Models.HealthRecord;
import com.group4.petcenter.Models.Model;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HealthRecordController implements Initializable{

    @FXML
    public Label eAnimalId;

    @FXML
    public DatePicker eDate;

    @FXML
    public Button eDeleteBtn;

    @FXML
    public TextArea eDiag;

    @FXML
    public Button eEditBtn;

    @FXML
    public Label eId;

    @FXML
    public TextArea eSym;

    @FXML
    public Button eUpdateBtn;

    @FXML
    public Label eUserId;
    
    private HealthRecord healthRecord = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String id = Model.getInstance().getViewFactory().selectedHealthRecordId;
        initHealthRecord(id);
        eUpdateBtn.setVisible(false);
        addListeners();
        disableGUI();
    }

    public void initHealthRecord(String id){
        ResultSet resultSet = Model.getInstance().getDB().getHealthRecord(id);
        HealthRecord temp = null;
        
        try {
            while(resultSet.next()){
                String userId = resultSet.getString("User_id");
                String animalId = resultSet.getString("Animal_id");
                String diagnosis = resultSet.getString("Diagnosis");
                String symptoms = resultSet.getString("Symptoms");
                String[] dateParts = resultSet.getString("Record_date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));

                temp = new HealthRecord(id, userId, animalId, date, diagnosis, symptoms);
                this.healthRecord = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        eId.setText(id);
        eUserId.setText(healthRecord.getUserId());
        eAnimalId.setText(healthRecord.getAnimalId());
        eDate.setValue(healthRecord.getRecordDate());
        eDiag.setText(healthRecord.getDiagnosis());
        eSym.setText(healthRecord.getSymptoms());
    }
    
    private void addListeners(){
        eEditBtn.setOnAction(e -> {
            eUpdateBtn.setVisible(true);
            eEditBtn.setVisible(false);
            eDate.setEditable(true);
            eDiag.setEditable(true);
            eSym.setEditable(true);
        });
        eUpdateBtn.setOnAction(e -> {
            eUpdateBtn.setVisible(false);
            eEditBtn.setVisible(true);
            disableGUI();
            
            healthRecord.setRecordDate(eDate.getValue());
            healthRecord.setDiagnosis(eDiag.getText());
            healthRecord.setSymptoms(eSym.getText());
            
            LocalDate date = healthRecord.getRecordDate();
            String diag = healthRecord.getDiagnosis();
            String sym = healthRecord.getSymptoms();
            Model.getInstance().getDB().updateHealthRecord(healthRecord.getHealthRecordId(), date, diag, sym);
            Model.getInstance().getViewFactory().refreshHealthRecords();
            close();
        });
        eDeleteBtn.setOnAction(e -> {
            delete();
        });
    }
    
    private void disableGUI(){
        eDate.setEditable(false);
        eDiag.setEditable(false);
        eSym.setEditable(false);
    }
    
    private void delete(){
        Model.getInstance().getDB().deleteHealthRecord(healthRecord.getHealthRecordId());
    }
    
    private void close(){
        Stage stage  = (Stage) eAnimalId.getScene().getWindow();
        stage.close();
    }
}

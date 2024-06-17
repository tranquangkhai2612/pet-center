package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Controllers.ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HealthRecordDAO {
   

    public boolean addHealthRecord(HealthRecord healthRecord) {
       String query = "INSERT INTO Health_Record (Health_record_id, User_id, Animal_id, Record_date, Diagnosis, Symptoms) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
              preparedStatement.setString(1, healthRecord.getRecordId());
             preparedStatement.setString(2, healthRecord.getUserId());
             preparedStatement.setString(3, healthRecord.getAnimalId());
               preparedStatement.setDate(4, java.sql.Date.valueOf(healthRecord.getDate())); // Chuyển đổi từ String sang Date
              preparedStatement.setString(5, healthRecord.getDiagnosi());
              preparedStatement.setString(6, healthRecord.getSymptoms());
            
             int rowsInserted =  preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Trả về false nếu có ngoại lệ xảy ra trong quá trình thực thi
            return false;
        }
    }
}

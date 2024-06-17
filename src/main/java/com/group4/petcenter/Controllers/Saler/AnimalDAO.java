package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Controllers.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnimalDAO {
    public ObservableList<Animal> getAllAnimals() {
        ObservableList<Animal> animalList = FXCollections.observableArrayList();
        String query = "SELECT Animal_id, Animal_name, Gender, Birthdate, Breed, Customer_id FROM Animals";

        try (Connection connection = new ConnectDB().getConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Animal animal = new Animal(
                        resultSet.getString("Animal_id"),
                        resultSet.getString("Animal_name"),
                        resultSet.getString("Gender"),
                        resultSet.getDate("Birthdate").toString(),
                        resultSet.getString("Breed"),
                      resultSet.getString("Customer_id")
                );
                animalList.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }

    public boolean addAnimal(Animal animal) {
        String query = "INSERT INTO Animals (Animal_id, Animal_name, Gender, Birthdate, Breed, Customer_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Thiết lập các tham số cho PreparedStatement
            preparedStatement.setString(1, animal.getAnimalId());
            preparedStatement.setString(2, animal.getAnimalName());
            preparedStatement.setString(3, animal.getGender());
            preparedStatement.setDate(4, java.sql.Date.valueOf(animal.getBirthdate())); // Chuyển đổi từ String sang Date
            preparedStatement.setString(5, animal.getBreed());
            
             preparedStatement.setString(6, animal.getCustomerId());

            int rowsInserted = preparedStatement.executeUpdate();

            // Trả về true nếu động vật được thêm thành công
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Trả về false nếu có ngoại lệ xảy ra trong quá trình thực thi
            return false;
        }
    }
     public boolean updateAnimal(Animal animal) {
    String query = "UPDATE Animals SET Animal_name = ?, Gender = ?, Birthdate = ?, Breed = ?, Customer_id = ? WHERE Animal_id = ?";

    try (Connection connection = new ConnectDB().getConnect();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, animal.getAnimalName());
        preparedStatement.setString(2, animal.getGender());
        preparedStatement.setDate(3, java.sql.Date.valueOf(animal.getBirthdate()));
        preparedStatement.setString(4, animal.getBreed());
        preparedStatement.setString(5, animal.getCustomerId());
        preparedStatement.setString(6, animal.getAnimalId());

        int rowsUpdated = preparedStatement.executeUpdate();

        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


   public boolean deleteAnimal(String animalId) {
    String query = "DELETE FROM Animals WHERE Animal_id = ?";

    try (Connection connection = new ConnectDB().getConnect();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, animalId);

        int rowsDeleted = preparedStatement.executeUpdate();

        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}

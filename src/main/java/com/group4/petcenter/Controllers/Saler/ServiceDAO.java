package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Controllers.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class ServiceDAO {
    public ObservableList<Service> getAllServices() {
        ObservableList<Service> serviceList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Service";

        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Service service = new Service(
                        resultSet.getString("Service_id"),
                        resultSet.getString("Name"),
                        resultSet.getBigDecimal("Price")
                );
                serviceList.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList;
    }
}

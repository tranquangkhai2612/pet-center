package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Controllers.ConnectDB;
import com.group4.petcenter.Controllers.Saler.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    public ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String query = "SELECT Customer_id, Customer_name, Gender, Address, Phone FROM Customers";

        try (Connection connection = new ConnectDB().getConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("Customer_id"),
                        resultSet.getString("Customer_name"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO Customers (Customer_id, Customer_name, Gender, Address, Phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            // Thiết lập các tham số cho PreparedStatement
            preparedStatement.setString(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setString(3, customer.getGender());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhone());

            // Thực thi truy vấn
            int rowsInserted = preparedStatement.executeUpdate();

            // Trả về true nếu khách hàng được thêm thành công
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Trả về false nếu có ngoại lệ xảy ra trong quá trình thực thi
            return false;
        }
    }
       public boolean updateCustomer(Customer customer) {
          String query = "UPDATE Customers SET Customer_name = ?, Gender = ?, Address = ?, Phone = ? WHERE Customer_id = ?";

        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for PreparedStatement
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getGender());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getCustomerId());

            // Execute query
            int rowsUpdated = preparedStatement.executeUpdate();

            // Return true if customer was successfully updated
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Return false if an exception occurred during execution
            return false;
        }
    }

    public boolean deleteCustomer(Customer customer) {
    String query = "DELETE FROM Customers WHERE Customer_id = ?";

    try (Connection connection = new ConnectDB().getConnect();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        // Set parameters for PreparedStatement
        preparedStatement.setString(1, customer.getCustomerId());

        // Execute query
        int rowsDeleted = preparedStatement.executeUpdate();

        // Return true if customer was successfully deleted
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        // Return false if an exception occurred during execution
        return false;
    }
}

}

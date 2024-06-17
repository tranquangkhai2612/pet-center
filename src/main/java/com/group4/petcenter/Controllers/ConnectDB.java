/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers;

import com.group4.petcenter.Controllers.Saler.Customer;
import com.group4.petcenter.Models.Animal;
import com.group4.petcenter.Models.Model;
import com.group4.petcenter.Models.Product;
import com.group4.petcenter.Models.Service;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class ConnectDB {
    Connection con = null;
    
    public Connection getConnect()
    {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PetCenter;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String pass = "123";
        
        try {
            //load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //connect to db
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Cannot connect");
        }
        
        return con;
    }
    
    public ConnectDB(){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PetCenter;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String pass = "123";
        
        try {
            //load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //connect to db
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Cannot connect");
        }        
    }
    
    // ADMIN
    public ResultSet getHealthRecordsData(){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Health_Record;");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }

        return resultSet;
    }
    
    public ResultSet getHealthRecord(String id){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Health_Record WHERE Health_record_id='" + id + "';");
            
        }catch (Exception e){
            
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public ResultSet getHealthRecordItems(String hrId){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Health_Record_Items WHERE Health_record_id='" + hrId + "';");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public void updateHealthRecord(String id, LocalDate date, String diag, String sym){
        Statement statement;
        java.sql.Date newDate = Date.valueOf(date);
        try {
            statement = con.createStatement();
            statement.execute("UPDATE Health_Record SET Record_date='" + newDate + "', Diagnosis='" + diag + "', Symptoms='" + sym + "'  WHERE Health_record_id='" + id + "';");
        }catch (SQLException e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void updateHealthRecordItem(String id, String Products_id, String Service_id, int quantity){
        Statement statement;
        
        try {
            statement = con.createStatement();
            if (Products_id.isEmpty()) {
                statement.execute("UPDATE Health_Record_Items SET Products_id=NULL, Service_id='" + Service_id + "', Quantity=" + quantity + "  WHERE Health_record_item_id='" + id + "';");
            }else{
                statement.execute("UPDATE Health_Record_Items SET Products_id='" + Products_id + "', Service_id=NULL, Quantity=" + quantity + "  WHERE Health_record_item_id='" + id + "';");
            }  
        }catch (SQLException e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void updateAnimalById(String id, String name, String gender, LocalDate date, String breed){
        Statement statement;
        java.sql.Date newDate = Date.valueOf(date);
        try {
            statement = con.createStatement();
            statement.execute("UPDATE Animals SET Animal_name='" + name + "', Gender='" + gender + "', Birthdate='" + newDate + "', Breed='" + breed + "'  "
                    + "WHERE Animal_id='" + id + "';");
        }catch (SQLException e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteHealthRecord(String id){
        Statement statement;
        
        try {
            statement = con.createStatement();
            statement.execute("DELETE FROM Health_Record WHERE Health_record_id='" + id + "';");
        }catch (SQLException e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteHealthRecordItem(String id){
        Statement statement;
        
        try {
            statement = con.createStatement();
            statement.execute("DELETE FROM Health_Record_Items WHERE Health_record_item_id='" + id + "';");
        }catch (SQLException e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ResultSet getProduct(String productId){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Products WHERE Products_id='" + productId + "';");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public Product getProductById(String id){
        Product product = null;
        ResultSet resultSet = getProduct(id);
        
        try {
            while(resultSet.next()){
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                String[] dateParts = resultSet.getString("Expired_date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("Price"));
                
                product = new Product(id, name, quantity, date, price);
            }
        } catch (SQLException e) {
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return product;
    }
    
    public ResultSet getService(String serviceId){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Service WHERE Service_id='" + serviceId + "';");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public ResultSet getAnimalResultSetById(String id){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Animals WHERE Animal_id='" + id + "';");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public ResultSet getCustomerResultSetById(String id){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Customers WHERE Customer_id='" + id + "';");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public ResultSet getProductsData(){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Products;");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }

        return resultSet;
    }
    
    public ObservableList<Product> getProducts(){
        ObservableList<Product> list = FXCollections.observableArrayList();
        ResultSet resultSet = getProductsData();
        
        try {
            while(resultSet.next()){
                String id = resultSet.getString("Products_id");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                String[] dateParts = resultSet.getString("Expired_date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("Price"));
                
                Product product = new Product(id, name, quantity, date, price);
                list.add(product);
            }
        } catch (SQLException e) {
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }
    
    public void createProduct(Product product) {
        String sql = "INSERT INTO Products (Products_id, Name, Quantity, Expired_date, Price) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, product.getProductsId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDate(4, java.sql.Date.valueOf(product.getExpiredDate()));
            preparedStatement.setDouble(5, product.getPrice().doubleValue());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("A new product was inserted successfully!");
                Model.getInstance().getAlertMessage().successMessage("A new product was inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getMessage());
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
        }
    }
    
    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET Name = ?, Quantity = ?, Expired_date = ?, Price = ? WHERE Products_id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDate(3, java.sql.Date.valueOf(product.getExpiredDate()));
            preparedStatement.setDouble(4, product.getPrice().doubleValue());
            preparedStatement.setString(5, product.getProductsId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product was updated successfully!");
                Model.getInstance().getAlertMessage().successMessage("Product was updated successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
        }
    }
    
    public void deleteProductById(String productId) {
        String sql = "DELETE FROM Products WHERE Products_id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product was deleted successfully!");
                Model.getInstance().getAlertMessage().successMessage("Product was deleted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
        }
    }


    
    public ResultSet getServicesData(){
        Statement statement;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Service;");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }

        return resultSet;
    }
    
    public ObservableList<Service> getServices(){
        ObservableList<Service> list = FXCollections.observableArrayList();
        ResultSet resultSet = getServicesData();
        
        try {
            while(resultSet.next()){
                String id = resultSet.getString("Service_id");
                double price = resultSet.getDouble("Price");
                String name = resultSet.getString("Name");
                
                Service service = new Service(id, price, name);
                list.add(service);
            }
        } catch (SQLException e) {
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }
    
    public Service getServiceById(String id){
        Service service = null;
        ResultSet resultSet = getService(id);
        
        try {
            while(resultSet.next()){
                double price = resultSet.getDouble("Price");
                String name = resultSet.getString("Name");
                
                service = new Service(id, price, name);
            }
        } catch (SQLException e) {
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return service;
    }
    
    public void createService(Service service) {
        String sql = "INSERT INTO Service (Service_id, Price, Name) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, service.getServiceId());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.setString(3, service.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("A new service was inserted successfully!");
                Model.getInstance().getAlertMessage().successMessage("A new service was inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting service: " + e.getMessage());
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
        }
    }
    
    public void updateService(Service service) {
        String sql = "UPDATE Service SET Price = ?, Name = ? WHERE Service_id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setDouble(1, service.getPrice());
            preparedStatement.setString(2, service.getName());
            preparedStatement.setString(3, service.getServiceId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Service was updated successfully!");
                Model.getInstance().getAlertMessage().successMessage("Service was updated successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating service: " + e.getMessage());
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
        }
    }

    public void deleteService(String serviceId) {
        String sql = "DELETE FROM Service WHERE Service_id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, serviceId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Service was deleted successfully!");
                Model.getInstance().getAlertMessage().successMessage("Service was deleted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting service: " + e.getMessage());
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
        }
    }
    
    public Animal getAnimalById(String id){
        Animal animal = null;
        ResultSet resultSet = getAnimalResultSetById(id);
        
        try {
            while(resultSet.next()){
                String gender = resultSet.getString("Gender");
                String name = resultSet.getString("Animal_name");
                String breed = resultSet.getString("Breed");
                String[] dateParts = resultSet.getString("Birthdate").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String cusId = resultSet.getString("Customer_id");
                
                animal = new Animal(id, name, gender, date, breed, cusId);
            }
        } catch (SQLException e) {
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return animal;
    }
    
    public Customer getCustomerById(String id){
        Customer customer = null;
        ResultSet resultSet = getCustomerResultSetById(id);
        
        try {
            while(resultSet.next()){
                String name = resultSet.getString("Customer_name");
                String gender = resultSet.getString("Gender");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                
                customer = new Customer(id, name, gender, address, phone);
            }
        } catch (SQLException e) {
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
        
        return customer;
    }
    
    public void createHRIProduct(String hriId, String hrId, String productId, int quantity){
        Statement statement;
        
        try {
            statement = con.createStatement();
            statement.execute("INSERT INTO Health_Record_Items (Health_record_item_id, Health_record_id, Products_id, Service_id, Quantity) "
                    + "VALUES ('" + hriId + "', '" + hrId + "', '" + productId + "', NULL, " + quantity + ");");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void createHRIService(String hriId, String hrId, String serviceId, int quantity){
        Statement statement;
        
        try {
            statement = con.createStatement();
            statement.execute("INSERT INTO Health_Record_Items (Health_record_item_id, Health_record_id, Products_id, Service_id, Quantity) "
                    + "VALUES ('" + hriId + "', '" + hrId + "', NULL, '" + serviceId + "', " + quantity + ");");
            
        }catch (Exception e){
            Model.getInstance().getAlertMessage().errorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    // STAFF
    
    
    
    // UTILITY
    public String getCurrentDateTimeString() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd/HH/mm/ss");
        
        // Format the current date and time
        String formattedDateTime = now.format(formatter);
        formattedDateTime = formattedDateTime.replace("/", "");
        
        return formattedDateTime;
    }
}
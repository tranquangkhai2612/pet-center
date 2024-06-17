/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Saler;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author LENOVO
 */
public class UpdateCustomerController {

 
    public Customer currentCustomer;
    @FXML
    public Button btn_update_customer;
    @FXML
    public HBox tf_gender;
    @FXML
    public RadioButton rb_male;
    @FXML
    public ToggleGroup tgGender;
    @FXML
    public RadioButton rb_female;
    @FXML
    public TextField tf_customer_id;
    @FXML
    public TextField tf_customer_phone;
    @FXML
    public TextField tf_customer_address;
    @FXML
    public TextField tf_customer_name;
    @FXML
    public AnchorPane mainPane;

    public void setCustomerData(Customer customer) {
        this.currentCustomer = customer;
        tf_customer_id.setText(customer.getCustomerId());
        tf_customer_name.setText(customer.getCustomerName());
         if (customer.getGender().equalsIgnoreCase("Male")) {
        rb_male.setSelected(true);
    } else {
        rb_female.setSelected(true);
    }
        tf_customer_address.setText(customer.getAddress());
        tf_customer_phone.setText(customer.getPhone());
    }

    @FXML
   public void handleUpdateCustomer(ActionEvent event) {
    currentCustomer.setCustomerName(tf_customer_name.getText());
    currentCustomer.setGender(rb_male.isSelected() ? "Male" : "Female");
    currentCustomer.setAddress(tf_customer_address.getText());
    currentCustomer.setPhone(tf_customer_phone.getText());

    CustomerDAO customerDAO = new CustomerDAO();
     if (customerDAO.updateCustomer(currentCustomer)) {
        // Hiển thị thông báo cập nhật thành công
        showAlert("Success", "Customer information updated successfully.");

        // Thay đổi nội dung của mainPane thành trang danh sách khách hàng
        switchToCustomerListPage();
    } else {
        // Hiển thị thông báo lỗi
        showAlert("Error", "Failed to update customer information.");
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
   public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


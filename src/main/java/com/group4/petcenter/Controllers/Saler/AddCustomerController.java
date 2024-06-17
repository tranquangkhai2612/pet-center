package com.group4.petcenter.Controllers.Saler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddCustomerController implements Initializable {

    public CustomerDAO customerDAO;

    @FXML
    public TextField tf_customer_id;
    @FXML
    public TextField tf_customer_name;
    @FXML
    public TextField tf_customer_phone;
    @FXML
    public TextField tf_customer_address;
    @FXML
    public RadioButton rb_male;
    @FXML
    public RadioButton rb_female;
    @FXML
    public HBox tf_gender;
    @FXML
    public ToggleGroup tgGender;
    @FXML
    public Button btn_create_customer;
    @FXML
    public AnchorPane mainPane;

    @Override
     public void initialize(URL url, ResourceBundle rb) {
        customerDAO = new CustomerDAO();

        btn_create_customer.setOnAction(event -> {
            String id = tf_customer_id.getText();
            String name = tf_customer_name.getText();
            String gender = rb_male.isSelected() ? "Male" : "Female";
            String address = tf_customer_address.getText();
            String phone = tf_customer_phone.getText();

            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
                return;
            }

            Customer newCustomer = new Customer(id, name, gender, address, phone);
            boolean success = customerDAO.addCustomer(newCustomer);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");

                // Làm mới danh sách khách hàng
                if (customerController != null) {
                    customerController.loadCustomers();
                }

                openCustomerPage();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed", "Failed to add customer.");
            }
        });
    }
    public CustomerController customerController;

    public void setCustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }
    public void setParentController(CustomerController parentController) {
    // Thực hiện implement ở đây
}
public void openCustomerPage() {
    // Load FXML của trang khách hàng
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/Customer.fxml"));
    try {
        Parent root = loader.load();

        // Tạo scene mới từ root
        Scene scene = new Scene(root);

        // Lấy stage hiện tại và set scene mới
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

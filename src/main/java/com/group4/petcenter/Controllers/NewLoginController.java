/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers;

import com.group4.petcenter.Controllers.ConnectDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author vyvt
 */
public class NewLoginController {

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    private Connection connect;
    private PreparedStatement prepare, prepareCheckId;
    private ResultSet result, resultCheckId;

    private AlertMessage alert = new AlertMessage();

    public void loginAccount() {
        if (login_username.getText().isEmpty() && login_password.getText().isEmpty()){
            alert.errorMessage("Fill Username and Password");
        }
        else if (login_username.getText().isEmpty()){
            alert.errorMessage("Empty Username");
        }
        else if(login_password.getText().isEmpty()){
            alert.errorMessage("Empty Password");
        } 
        else {
            String sql = "SELECT * FROM Users WHERE User_name = ? AND Pass_word = ?";
            ConnectDB db = new ConnectDB();
            connect = db.getConnect();
            try {
                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();
                if (result.next()) {
                    // IF CORRECT USERNAME AND PASSWORD
                    alert.successMessage("Login Successfully!");

                    // LINK MAIN FORM FOR ADMIN
                    Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Admin.fxml"));
                    Stage stage = new Stage();

                    stage.setTitle("Pet Center");
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    // IF WRONG USERNAME OR PASSWORD
                    alert.errorMessage("Incorrect Username/Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

}

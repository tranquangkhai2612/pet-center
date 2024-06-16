/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Controllers.AlertMessage;
import com.group4.petcenter.Controllers.ConnectDB;
import com.group4.petcenter.Models.Users;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author vyvt
 */
public class UsersController implements Initializable {
    private Connection connect;

    @FXML
    private AnchorPane addUser_form;
    @FXML
    private Button addUser_addBtn;
    @FXML
    private Button addUser_clearBtn;
    @FXML
    private Button addUser_removeBtn;
    @FXML
    private TextField tfuserid;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tffullname;
    @FXML
    private TextField tfpassword;
    @FXML
    private DatePicker dpcreatedate;
    @FXML
    public TextField txtSearchBar;
    @FXML
    private TableView<Users> addUser_tableView;
    @FXML
    private TableColumn<?, ?> addUser_col_userid;
    @FXML
    private TableColumn<?, ?> addUser_col_username;
//    @FXML
//    private TableColumn<?, ?> addUser_col_password;
    @FXML
    private TableColumn<?, ?> addUser_col_fullname;
    @FXML
    private TableColumn<?, ?> addUser_col_role;
    @FXML
    private TableColumn<?, ?> addUser_col_gender;
    @FXML
    private TableColumn<?, ?> addUser_col_createddate;
    @FXML
    private AnchorPane subjectHandle_form;
    @FXML
    private ComboBox<?> subjecthandle_subject;
    @FXML
    private Button subjecthandle_addBtn;
    @FXML
    private Button subjecthandle_clearBtn;
    @FXML
    private Button subjecthandle_removeBtn;
    @FXML
    private ComboBox<?> subjecthandle_code;
    @FXML
    private ComboBox<?> subjecthandle_status;
    @FXML
    private TableView<?> subjecthandle_tableView;
    @FXML
    private TableColumn<?, ?> subjecthandle_col_subjectCode;
    @FXML
    private TableColumn<?, ?> subjecthandle_col_subjectName;
    @FXML
    private TableColumn<?, ?> subjecthandle_col_dateInsert;
    @FXML
    private TableColumn<?, ?> subjecthandle_col_status;   
    @FXML
    private ComboBox<String> cbrole;
    private String[] role = {"Staff", "Admin"};
    @FXML
    private ComboBox<String> cbgender;
    private String[] gender = {"female", "male"};
    private AlertMessage alert = new AlertMessage();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbrole.getItems().addAll(role);
        cbgender.getItems().addAll(gender);
        showUsers();
        searchFilter();
    }  
    
    public void executeQuery(String query){
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        
        Statement st;
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public void showUsers(){
        ObservableList<Users> list = getUsers() ;
        addUser_col_userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        addUser_col_username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        addUser_col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addUser_col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        addUser_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addUser_col_createddate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        addUser_tableView.setItems(list);
    }
    
     public ObservableList<Users> getUsers() {
        ObservableList<Users> userList = FXCollections.observableArrayList();
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        String query = "SELECT User_id, User_name, Pass_word, Full_name, [Role], Gender, Create_date FROM Users";
        Statement st = null;
        ResultSet rs = null;

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Users user = new Users();
                user.setUserId(rs.getString("User_id"));
                user.setUserName(rs.getString("User_name"));
                user.setPassword(rs.getString("Pass_word"));
                user.setFullName(rs.getString("Full_name"));
                user.setRole(rs.getString("Role"));
                user.setGender(rs.getString("Gender"));
                user.setCreateDate(rs.getDate("Create_date"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (st != null) try { st.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return userList;       
    }
    
    @FXML
    private void addUser(ActionEvent event) {
        String userId = tfuserid.getText();
        String userName = tfusername.getText();
        String passWord = tfpassword.getText();
        String fullName = tffullname.getText();
        String role = cbrole.getValue();
        String gender = cbgender.getValue(); 
        LocalDate selectedDate = dpcreatedate.getValue();
        String createdDate;
        
        if (selectedDate != null) {
        createdDate = selectedDate.toString();
        } else {
            createdDate = LocalDate.now().toString();
        }

        if (userId == null || userId.isEmpty() ||
            userName == null || userName.isEmpty() ||
            passWord == null || passWord.isEmpty() ||
            role == null || role.isEmpty() ||
            gender == null || gender.isEmpty()) {
            alert.errorMessage("User ID, Username, Password, Role, Gender can't be null or empty");
            return;
        }
        if (userIdExists(userId)) {
            alert.errorMessage("User ID already exists in the database");
            return;
        }
        if (passWord.length() < 6) {
            alert.errorMessage("Password input must have more than 5 characters");
            return;
        }
        if (!userId.matches("S\\d{2}")) {
            alert.errorMessage(" User ID must start with S and is followed by exactly 2 numbers");
            return;
        }
        
        String query = "INSERT INTO Users(User_id, User_name, Pass_word, Full_name, Role, Gender, Create_date) " +
                   "VALUES ('" + userId + "', '" + userName + "', '" + passWord + "', '" + fullName + "', '" +
                   role + "', '" + gender + "', '" + createdDate + "')";
        executeQuery(query);
        alert.successMessage("Add user successfully");
        showUsers(); 
        searchFilter();
    }
    
    @FXML
    private void clearFields(ActionEvent event) {
        tfuserid.clear();
        tfusername.clear();
        tfpassword.clear();
        tffullname.clear();
        cbrole.getSelectionModel().clearSelection();
        cbgender.getSelectionModel().clearSelection();
        dpcreatedate.setValue(null);
        searchFilter();
    }
    
    @FXML
    private void selectUser(MouseEvent event) {
        Users p = addUser_tableView.getSelectionModel().getSelectedItem();
            if (p != null) {
            tfuserid.setText(String.valueOf(p.getUserId()));
            tfusername.setText(p.getUserName());
            tfpassword.setText(p.getPassword());
            tffullname.setText(p.getFullName());
            cbrole.setValue(p.getRole());
            cbgender.setValue(p.getGender());           
            Date createDate = p.getCreateDate();
            LocalDate localDate = createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            dpcreatedate.setValue(localDate);
        }
        searchFilter();
    }
    
    @FXML
    private void updateUser(ActionEvent event) {
        String userId = tfuserid.getText();
        String userName = tfusername.getText();
        String passWord = tfpassword.getText();
        String fullName = tffullname.getText();
        String role = cbrole.getValue();
        String gender = cbgender.getValue(); 

        if (userId == null || userId.isEmpty() ||
            userName == null || userName.isEmpty() ||
            passWord == null || passWord.isEmpty() ||
            role == null || role.isEmpty() ||
            gender == null || gender.isEmpty()) {
            alert.errorMessage("User ID, Username, Password, Role, Gender can't be null or empty");
            return;
        }
        if (passWord.length() < 6) {
            alert.errorMessage("Password input must have more than 5 characters");
            return;
        }
        if (!userId.matches("S\\d{2}")) {
            alert.errorMessage(" User ID must start with S and is followed by exactly 2 numbers");
            return;
        }
                
        String query = "Update Users set User_id = '" + userId + "', User_name = '" + userName+ "', Pass_word = '" + passWord
                + "', Full_name = '" + fullName+ "', Role = '" + role+ "', Gender = '" + gender
                +"' where User_id = '" + userId + "'";
        
        executeQuery(query);
        alert.successMessage("Update user successfully");
        showUsers();
        searchFilter();
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        String userId = tfuserid.getText();
        if ("S01".equals(userId)) {
        alert.errorMessage("User 'S01' cannot be deleted.");
        return;
    }
        String query = "DELETE FROM Users WHERE User_id = '" + userId + "'";
         if (!alert.confirmationMessage("Are you sure?")) {
            return;
        }
        executeQuery(query);       
        showUsers();
        alert.successMessage("Delete user successfully");
        searchFilter();
    }
    
    private boolean userIdExists(String userId){     
        try{
            ConnectDB connect = new ConnectDB();
            Connection con = connect.getConnect();
            String query = "SELECT COUNT(*) FROM Users WHERE User_id = '" + userId + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }catch (SQLException e) {
            alert.errorMessage("Database error: " + e.getMessage());
        }
        return false;
    }
           
    private void searchFilter() {
        FilteredList<Users> filterData = new FilteredList<>(getUsers(), e -> true);
        txtSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return user.getUserId().toLowerCase().contains(lowerCaseFilter)
                        || user.getUserName().toLowerCase().contains(lowerCaseFilter)
                        || user.getFullName().toLowerCase().contains(lowerCaseFilter)
                        || user.getRole().toLowerCase().contains(lowerCaseFilter)
                        || user.getGender().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Users> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(addUser_tableView.comparatorProperty());
        addUser_tableView.setItems(sortedData);
    }
}

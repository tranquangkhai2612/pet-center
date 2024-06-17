package com.group4.petcenter.Controllers.Saler;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.*;

public class CustomerController implements Initializable {

    public CustomerDAO customerDAO;
    public AnimalDAO animalDAO;
    @FXML
    public TableColumn<Animal, String> tb_animal_id;
    @FXML
    public TableColumn<Animal, String> tb_animal_name;
    @FXML
    public TableColumn<Animal, String> tb_animal_gender;
    @FXML
    public TableColumn<Animal, String> tb_animal_birthdate;
    @FXML
    public TableColumn<Animal, String> tb_animal_breed;
    @FXML
    public TableColumn<Animal, String> tb_animal_customer;
    @FXML
    public TableView<Animal> tb_animal_view;
    @FXML
    public TableColumn<Animal, Void> tb_animal_action;
    @FXML
    public TableView<Customer> tb_customer_view;
    @FXML
    public TableColumn<Customer, String> tb_customer_id;
    @FXML
    public TableColumn<Customer, String> tb_customer_name;
    @FXML
    public TableColumn<Customer, String> tb_customer_gender;
    @FXML
    public TableColumn<Customer, String> tb_customer_address;
    @FXML
    public TableColumn<Customer, String> tb_customer_phone;
    @FXML
    public TableColumn<Customer, Void> tb_customer_action;
    @FXML
    public TextField tf_search_customer;
    @FXML
    public TextField btn_search_animal;

    public ObservableList<Customer> customerList;
    public ObservableList<Animal> animalList;
    @FXML
    public Button btn_add_customer;
    @FXML
    public AnchorPane mainPane;
 public AddAnimalController addAnimalController;

 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tb_customer_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tb_customer_name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tb_customer_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tb_customer_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tb_customer_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tb_animal_id.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        tb_animal_name.setCellValueFactory(new PropertyValueFactory<>("animalName"));
        tb_animal_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tb_animal_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        tb_animal_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        tb_animal_customer.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        try {
        customerDAO = new CustomerDAO();
        animalDAO = new AnimalDAO();

        customerList = customerDAO.getAllCustomers();
        tb_customer_view.setItems(customerList);

        animalList = animalDAO.getAllAnimals();
        tb_animal_view.setItems(animalList);
    } catch (Exception e) {
        e.printStackTrace();
    }

    tf_search_customer.textProperty().addListener((observable, oldValue, newValue) -> {
        searchCustomer();
    });
    
    btn_search_animal.textProperty().addListener((observable, oldValue, newValue) -> {
        searchAnimal();
    });

    setupTable();
    loadCustomers();
    loadAnimals();
}
  

    
    public void setupTable() {
        tb_customer_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tb_customer_name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tb_customer_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tb_customer_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tb_customer_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tb_customer_action.setCellFactory(createCustomerActionCellFactory());

        tb_animal_id.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        tb_animal_name.setCellValueFactory(new PropertyValueFactory<>("animalName"));
        tb_animal_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tb_animal_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        tb_animal_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        tb_animal_customer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tb_animal_action.setCellFactory(createAnimalActionCellFactory());
    }

    public Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>> createCustomerActionCellFactory() {
        return new Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>>() {
            @Override
            public TableCell<Customer, Void> call(final TableColumn<Customer, Void> param) {
                final TableCell<Customer, Void> cell = new TableCell<Customer, Void>() {

                    public final Button btnUpdate = new Button("Update");
                    
                    public final Button btnDelete = new Button("Delete");
                    public final Button btnAddAnimal = new Button("Add New Animal");

                    {
                        btnUpdate.setOnAction((ActionEvent event) -> {
                            Customer customer = getTableView().getItems().get(getIndex());
                            handleUpdateCustomer(customer);
                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            Customer customer = getTableView().getItems().get(getIndex());
                            handleDeleteCustomer(customer);
                        });
                          btnAddAnimal.setOnAction((ActionEvent event) -> {
                        Customer customer = getTableView().getItems().get(getIndex());
                        handleAddAnimal(customer);
                    });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(btnUpdate, btnDelete,btnAddAnimal);
                            hBox.setSpacing(10);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
    }

    public Callback<TableColumn<Animal, Void>, TableCell<Animal, Void>> createAnimalActionCellFactory() {
        return new Callback<TableColumn<Animal, Void>, TableCell<Animal, Void>>() {
            @Override
            public TableCell<Animal, Void> call(final TableColumn<Animal, Void> param) {
                final TableCell<Animal, Void> cell = new TableCell<Animal, Void>() {

                    public final Button btnUpdate = new Button("Update");
                    public final Button btnDelete = new Button("Delete");
                    public final Button btnCreate = new Button("Create Health Record");

                    {
                        btnUpdate.setOnAction((ActionEvent event) -> {
                            Animal animal = getTableView().getItems().get(getIndex());
                            handleUpdateAnimal(animal);
                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            Animal animal = getTableView().getItems().get(getIndex());
                            handleDeleteAnimal(animal);
                        });
                        btnCreate.setOnAction((ActionEvent event) -> {
                          Animal animal = getTableView().getItems().get(getIndex());
                            handleCreateHR(animal);
                            });
                    
                        
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(btnUpdate, btnDelete,btnCreate);
                            hBox.setSpacing(10);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
    }

   public void loadCustomers() {
    ObservableList<Customer> customers = customerDAO.getAllCustomers();
    tb_customer_view.setItems(customers);
}


    public void loadAnimals() {
        animalList = animalDAO.getAllAnimals();
        tb_animal_view.setItems(animalList);
    }

        public void searchCustomer() {
        String keyword = tf_search_customer.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            tb_customer_view.setItems(customerList);
            searchAnimalByCustomerId(""); // Đặt lại danh sách động vật khi trường tìm kiếm trống
            return;
        }

        ObservableList<Customer> filteredCustomers = FXCollections.observableArrayList();

        for (Customer customer : customerList) {
            if (customerContainsKeyword(customer, keyword)) {
                filteredCustomers.add(customer);
            }
        }

        tb_customer_view.setItems(filteredCustomers);

        if (filteredCustomers.size() == 1) {
            String customerId = filteredCustomers.get(0).getCustomerId();
            searchAnimalByCustomerId(customerId);
        } else {
            tb_animal_view.setItems(FXCollections.observableArrayList());
        }
    }

    public void searchAnimalByCustomerId(String customerId) {
        ObservableList<Animal> filteredAnimals = FXCollections.observableArrayList();

        for (Animal animal : animalList) {
            if (customerId.isEmpty() || animal.getCustomerId().equals(customerId)) {
                filteredAnimals.add(animal);
            }
        }

        tb_animal_view.setItems(filteredAnimals);
    }


    public void searchAnimal() {
        String keyword = btn_search_animal.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            tb_animal_view.setItems(animalList);
            return;
        }

        ObservableList<Animal> filteredAnimals = FXCollections.observableArrayList();

        for (Animal animal : animalList) {
            if (animalContainsKeyword(animal, keyword)) {
                filteredAnimals.add(animal);
            }
        }

        tb_animal_view.setItems(filteredAnimals);
    }

    public boolean customerContainsKeyword(Customer customer, String keyword) {
        return customer.getCustomerId().toLowerCase().contains(keyword) ||
               customer.getCustomerName().toLowerCase().contains(keyword) ||
               customer.getGender().toLowerCase().contains(keyword) ||
               customer.getAddress().toLowerCase().contains(keyword) ||
               customer.getPhone().toLowerCase().contains(keyword);
    }

    public boolean animalContainsKeyword(Animal animal, String keyword) {
        return animal.getAnimalId().toLowerCase().contains(keyword) ||
               animal.getAnimalName().toLowerCase().contains(keyword) ||
               animal.getGender().toLowerCase().contains(keyword) ||
               animal.getBirthdate().toLowerCase().contains(keyword) ||
               animal.getBreed().toLowerCase().contains(keyword) ||
               animal.getCustomerId().toLowerCase().contains(keyword);
    }

    @FXML
public void handleAddCustomerButton(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/Addcustomer.fxml"));
        Parent root = loader.load();
        
        // Lấy controller của AddCustomer
        AddCustomerController addCustomerController = loader.getController();
        
        // Truyền đối tượng CustomerController hiện tại
        addCustomerController.setCustomerController(this);

        mainPane.getChildren().setAll(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void handleUpdateCustomer(Customer customer) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/UpdateCustomer.fxml"));
        Parent root = loader.load();
        UpdateCustomerController updateCustomerController = loader.getController();
        updateCustomerController.setCustomerData(customer);
        
        // Thay đổi nội dung của mainPane thành trang cập nhật khách hàng
        mainPane.getChildren().setAll(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void handleDeleteCustomer(Customer customer) {
       // Hiển thị cảnh báo hoặc hỏi người dùng xác nhận trước khi xóa
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Delete Customer");
    alert.setContentText("Are you sure you want to delete this customer?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Người dùng xác nhận xóa, thực hiện xóa khách hàng
        if (customerDAO.deleteCustomer(customer)) {
            // Xóa thành công, làm mới danh sách khách hàng
            loadCustomers();
        } else {
            // Xóa thất bại, hiển thị thông báo lỗi
            showAlert("Error", "Failed to delete customer.");
        }
    }
    }
     
public void handleAddAnimal(Customer customer) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/AddAnimal.fxml"));
        Parent root = loader.load();

        AddAnimalController addAnimalController = loader.getController();
        addAnimalController.setCustomerList(customerList); 
        addAnimalController.setCustomerId(customer.getCustomerId()); // Truyền ID khách hàng

        mainPane.getChildren().setAll(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void handleCreateHR (Animal animal){
     try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/CreateHR.fxml"));
        Parent root = loader.load();

        CreateHRController createHRController = loader.getController();
        createHRController.setAnimalList(animalList); 
        createHRController.setAnimalId(animal.getAnimalId()); // Truyền ID khách hàng

        mainPane.getChildren().setAll(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void setAddAnimalController(AddAnimalController addAnimalController) {
    this.addAnimalController = addAnimalController;
}

    public void handleUpdateAnimal(Animal animal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/UpdateAnimal.fxml"));
            Parent root = loader.load();
            UpdateAnimalController updateAnimalController = loader.getController();
            updateAnimalController.setAnimalData(animal);

            // Change the content of mainPane to the update animal page
            mainPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteAnimal(Animal animal) {
        // Display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Animal");
        alert.setContentText("Are you sure you want to delete this animal?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed delete, perform the delete operation
            if (animalDAO.deleteAnimal(animal.getAnimalId())) {
                // Delete successful, refresh the animal list
                loadAnimals();
            } else {
                // Delete failed, display an error message
                showAlert("Error", "Failed to delete animal.");
            }
        }
    }
    


    public static void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    
}
}

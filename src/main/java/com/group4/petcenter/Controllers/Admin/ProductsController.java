/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Models.Model;
import com.group4.petcenter.Models.Product;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Trần Quang Khải
 */
public class ProductsController implements Initializable {
    @FXML
    public Button e_btn_cancel;

    @FXML
    public Button e_btn_create;

    @FXML
    public Button e_btn_create_new;

    @FXML
    public Button e_btn_delete;

    @FXML
    public Button e_btn_edit;

    @FXML
    public Button e_btn_update;

    @FXML
    public TableColumn<Product, String> e_col_date;

    @FXML
    public TableColumn<Product, String> e_col_id;

    @FXML
    public TableColumn<Product, String> e_col_name;

    @FXML
    public TableColumn<Product, Double> e_col_price;

    @FXML
    public TableColumn<Product, Integer> e_col_quantity;

    @FXML
    public DatePicker e_date;

    @FXML
    public TextField e_id;

    @FXML
    public TextField e_name;

    @FXML
    public TextField e_price;

    @FXML
    public TextField e_quantity;

    @FXML
    public TextField e_search;

    @FXML
    public TableView<Product> e_table;

    private Product selectedProduct = null;
    ObservableList<Product> filteredList = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProducts();
        addListeners();
        onCancel();
    }

    private void showProducts() {
        ObservableList<Product> list = Model.getInstance().getDB().getProducts();
        e_col_id.setCellValueFactory(new PropertyValueFactory<>("productsId"));
        e_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        e_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        e_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        e_col_date.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
        e_table.setItems(list);
    }
    
    private void addListeners(){
        e_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedProduct = e_table.getSelectionModel().getSelectedItem();
                onSelectedProduct();
                e_table.getSelectionModel().clearSelection();
            }
        });
        
        Model.getInstance().getViewFactory().addDoubleValidation(e_price);
        Model.getInstance().getViewFactory().addIntegerValidation(e_quantity);
        
        e_btn_create_new.setOnAction(e -> {
            onCreateNew();
        });
        
        e_btn_cancel.setOnAction(e -> {
            onCancel();
        });
        
        e_btn_edit.setOnAction(e -> {
            onEdit();
        });
        
        e_btn_create.setOnAction(e -> {
            onCreate();
        });
        
        e_btn_update.setOnAction(e -> {
            onUpdate();
        });
        
        e_btn_delete.setOnAction(e -> {
            onDelete();
        });
        
        e_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterProducts(newValue);
        });
    }
    
    private void filterProducts(String searchText) {
        ObservableList<Product> productList = Model.getInstance().getDB().getProducts();
        if (searchText == null || searchText.isEmpty()) {
            filteredList.setAll(productList);
        } else {
            filteredList.clear();
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    product.getProductsId().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(product);
                }
            }
        }
        e_table.setItems(productList);
    }
    
    private void onSelectedProduct(){
        e_id.setText(selectedProduct.getProductsId());
        e_name.setText(selectedProduct.getName());
        e_price.setText(selectedProduct.getPrice().toString());
        e_quantity.setText(String.valueOf(selectedProduct.getQuantity()));
        e_date.setValue(selectedProduct.getExpiredDate());
        onSelected();
    }
    
    private void onSelected(){
        e_btn_create.setVisible(false);
        e_btn_update.setVisible(false);
        e_btn_create_new.setVisible(true);
        e_btn_edit.setVisible(true);
        e_btn_cancel.setVisible(true);
        e_btn_delete.setVisible(true);
        
        e_id.setEditable(false);
        e_name.setEditable(false);
        e_price.setEditable(false);
        e_quantity.setEditable(false);
        e_date.setEditable(false);
    }
    
    private void onEdit(){
        e_id.setEditable(false);
        e_name.setEditable(true);
        e_price.setEditable(true);
        e_quantity.setEditable(true);
        e_date.setEditable(true);
        
        e_btn_update.setVisible(true);
        e_btn_cancel.setVisible(true);
        e_btn_edit.setVisible(false);
        e_btn_create_new.setVisible(false);
        e_btn_create.setVisible(false);
        e_btn_delete.setVisible(false);
    }
    
    private void onCancel(){
        e_id.setText("");
        e_name.setText("");
        e_price.setText("");
        e_quantity.setText("");
        e_date.setValue(LocalDate.now());
        
        selectedProduct = null;
        
        e_btn_create_new.setVisible(true);
        e_btn_create.setVisible(false);
        e_btn_edit.setVisible(false);
        e_btn_update.setVisible(false);
        e_btn_cancel.setVisible(false);
        e_btn_delete.setVisible(false);
        showProducts();
    }
    
    private void onCreateNew(){
        String id = "P" + Model.getInstance().getDB().getCurrentDateTimeString();
        
        e_id.setText(id);
        e_name.setText("");
        e_price.setText("");
        e_quantity.setText("");
        e_date.setValue(LocalDate.now());
        
        selectedProduct = null;
        e_id.setEditable(false);
        e_name.setEditable(true);
        e_price.setEditable(true);
        e_quantity.setEditable(true);
        e_date.setEditable(true);
        
        e_btn_create_new.setVisible(false);
        e_btn_create.setVisible(true);
        e_btn_edit.setVisible(false);
        e_btn_update.setVisible(false);
        e_btn_cancel.setVisible(true);
        e_btn_delete.setVisible(false);
    }
    
    private void onUpdate(){
        String id = e_id.getText();
        String name = e_name.getText();
        String quantityStr = e_quantity.getText();
        LocalDate date = e_date.getValue();
        
        if(name.isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty name!");
            return;
        }
        if(e_price.getText().isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty price!");
            return;
        }
        if(quantityStr.isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty quantity!");
            return;
        }
        
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(e_price.getText()));
        int quantity = Integer.parseInt(quantityStr);
       
        Product p = new Product(id, name, quantity, date, price);
        Model.getInstance().getDB().updateProduct(p);
        
        onCancel();   
    }
    
    private void onDelete(){
        Model.getInstance().getDB().deleteProductById(selectedProduct.getProductsId());
        onCancel();
    }
    
    private void onCreate(){
        String id = e_id.getText();
        String name = e_name.getText();
        String quantityStr = e_quantity.getText();
        LocalDate date = e_date.getValue();
        
        if(name.isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty name!");
            return;
        }
        if(e_price.getText().isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty price!");
            return;
        }
        if(quantityStr.isEmpty()){
            Model.getInstance().getAlertMessage().errorMessage("Empty quantity!");
            return;
        }
        
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(e_price.getText()));
        int quantity = Integer.parseInt(quantityStr);
       
        Product p = new Product(id, name, quantity, date, price);
        Model.getInstance().getDB().createProduct(p);
                
        onCancel();
    } 
    
}

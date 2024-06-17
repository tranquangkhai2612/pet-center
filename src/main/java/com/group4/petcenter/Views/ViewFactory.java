/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Views;

import com.group4.petcenter.Controllers.Admin.AddHRIProductController;
import com.group4.petcenter.Controllers.Admin.AddHRIServiceController;
import com.group4.petcenter.Controllers.Admin.AdminController;
import com.group4.petcenter.Controllers.Admin.AnimalController;
import com.group4.petcenter.Controllers.Admin.HealthRecordItemController;
import com.group4.petcenter.Controllers.Admin.HealthRecordsController;
import com.group4.petcenter.Models.Animal;
import com.group4.petcenter.Models.Enums.*;
import com.group4.petcenter.Models.HealthRecord;
import com.group4.petcenter.Models.HealthRecordItem;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Trần Quang Khải
 */
public class ViewFactory {
    private AccountTypeEnum mLoginAccountType;
    private final ObjectProperty<AdminMenuEnum> adminSelectedMenuItem;
    private final ObjectProperty<StaffMenuEnum> staffSelectedMenuItem;
    
    // Admin
    private AnchorPane mUsersView;
    private AnchorPane mHealthRecordsView;
    private AnchorPane mDiagnosisServicesView;
    private AnchorPane mProductsView;
    private AnchorPane mDashboardView;
    
    public String selectedHealthRecordId = "";
    
    private HealthRecordsController healthRecordsController;
    
    // STAFF
    private AnchorPane mCustomersView;
    private AnchorPane mPaymentView;
    
    public ViewFactory(){
        this.mLoginAccountType = AccountTypeEnum.ADMIN;
        this.adminSelectedMenuItem = new SimpleObjectProperty();
        this.staffSelectedMenuItem = new SimpleObjectProperty();
    }

    public AnchorPane getUsersView(){
        if(mUsersView == null){
            try {
                mUsersView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Users.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mUsersView;
    }
    
    public AnchorPane getDashboardView(){
        if(mDashboardView == null){
            try {
                mDashboardView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mDashboardView;
    }
    
    public AnchorPane getHealthRecordsView(){
        if(mHealthRecordsView == null){
            try {
                healthRecordsController = new HealthRecordsController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/HealthRecords.fxml"));
                loader.setController(healthRecordsController);
                mHealthRecordsView = loader.load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mHealthRecordsView;
    }
    
    public AnchorPane getDiagnosisServicesView(){
        if(mDiagnosisServicesView == null){
            try {
                mDiagnosisServicesView = new FXMLLoader(getClass().getResource("/Fxml/Admin/DiagnosisServices.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mDiagnosisServicesView;
    }
    
    public AnchorPane getProductsView(){
        if(mProductsView == null){
            try {
                mProductsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Products.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mProductsView;
    }
    
    public AnchorPane getCustomersView(){
        if(mCustomersView == null){
            try {
                mCustomersView = new FXMLLoader(getClass().getResource("/Fxml/Saler/Customer.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mCustomersView;
    }
    
    public AnchorPane getPaymentView(){
        if(mPaymentView == null){
            try {
                mPaymentView = new FXMLLoader(getClass().getResource("/Fxml/Saler/Product.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mPaymentView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Users.fxml"));
        createStage(loader);
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        createStage(loader);
    }
    
    public void showStaffWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/Staff.fxml"));
        createStage(loader);
    }
    
    public void showHealthRecordWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/HealthRecord.fxml"));
        createStage(loader);
    }
    
    public void showHealthRecordItemWindow(HealthRecordItem healthRecordItem){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/HealthRecordItem.fxml"));
        HealthRecordItemController hriController = new HealthRecordItemController(healthRecordItem);
        loader.setController(hriController);
        createStage(loader);
    }
    
    public void showAnimalWindow(Animal animal){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Animal.fxml"));
        AnimalController controller = new AnimalController(animal);
        loader.setController(controller);
        createStage(loader);
    }
    
    public void showHRIProductWindow(HealthRecord healthRecord){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/AddHRIProduct.fxml"));
        AddHRIProductController controller = new AddHRIProductController(healthRecord);
        loader.setController(controller);
        createStage(loader);
    }
    
    public void showHRIServiceWindow(HealthRecord healthRecord){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/AddHRIService.fxml"));
        AddHRIServiceController controller = new AddHRIServiceController(healthRecord);
        loader.setController(controller);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/pet.png"))));
        stage.setTitle("Pet Center");
        stage.show();
    }
    
    public void closeStage(Stage stage){
        stage.close();
    }
    
    public ObjectProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public ObjectProperty getStaffSelectedMenuItem(){
        return staffSelectedMenuItem;
    }
    
    public AccountTypeEnum getLoginAccountType() {
        return mLoginAccountType;
    }

    public void setLoginAccountType(AccountTypeEnum mLoginAccountType) {
        this.mLoginAccountType = mLoginAccountType;
    }
    
    public void refreshHealthRecords(){
        healthRecordsController.showHealthRecords();
    }
    
    public void refreshHealthRecordItems(){
        healthRecordsController.showHealthRecordItems();
    }
    
    public void refreshAnimal(String animalId){
        refreshHealthRecords();
        healthRecordsController.showAnimal(animalId);
    }
    
    public void addDoubleValidation(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[+-]?([0-9]*[.])?[0-9]*")) {
                    textField.setText(oldValue);
                }
            }
        });  
    }
    public void addIntegerValidation(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(oldValue);
                }
            }
        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Views;

import com.group4.petcenter.Controllers.Admin.AdminController;
import com.group4.petcenter.Models.Enums.*;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Trần Quang Khải
 */
public class ViewFactory {
    private AccountTypeEnum mLoginAccountType;
    private final ObjectProperty<AdminMenuEnum> adminSelectedMenuItem;
    
    // Admin
    private AnchorPane mUsersView;
    private AnchorPane mHealthRecordsView;
    private AnchorPane mDiagnosisServicesView;
    private AnchorPane mProductsView;

    // Salers
    private AnchorPane mProducts;
    private AnchorPane mServices;
    
    // Doctors
    private AnchorPane mPets;
    private AnchorPane mAddPet;
    
    public ViewFactory(){
        this.mLoginAccountType = AccountTypeEnum.ADMIN;
        this.adminSelectedMenuItem = new SimpleObjectProperty();
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
    
    public AnchorPane getHealthRecordsView(){
        if(mHealthRecordsView == null){
            try {
                mHealthRecordsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/HealthRecords.fxml")).load();
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

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        createStage(loader);
    }
    
    public void showStaffWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Staff/Staff.fxml"));
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
        stage.setTitle("Pet Center");
        stage.show();
    }
    
    public void closeStage(Stage stage){
        stage.close();
    }
    
    public ObjectProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AccountTypeEnum getLoginAccountType() {
        return mLoginAccountType;
    }

    public void setLoginAccountType(AccountTypeEnum mLoginAccountType) {
        this.mLoginAccountType = mLoginAccountType;
    }
    
    
}

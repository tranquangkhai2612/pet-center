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
    private AnchorPane mOverviewView;
    private AnchorPane mProductsView;
    private AnchorPane mDoctorsView;
    private AnchorPane mSalersView;

    // Salers
    private AnchorPane mProducts;
    private AnchorPane mServices;
    
    // Doctors
    private AnchorPane mPets;
    private AnchorPane mAddPet;
    
    public ViewFactory(){
        this.mLoginAccountType = AccountTypeEnum.SALERS;
        this.adminSelectedMenuItem = new SimpleObjectProperty();
    }

    public AnchorPane getOverviewView(){
        if(mOverviewView == null){
            try {
                mOverviewView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminOverview.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mOverviewView;
    }
    
    public AnchorPane getProductsView(){
        if(mProductsView == null){
            try {
                mProductsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminProducts.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mProductsView;
    }
    
    public AnchorPane getSalersView(){
        if(mDoctorsView == null){
            try {
                mDoctorsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminDoctors.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mDoctorsView;
    }
    
    public AnchorPane getDoctorsView(){
        if(mSalersView == null){
            try {
                mSalersView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminSalers.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mSalersView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        createStage(loader);
    }
    
    public void showDoctorWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Doctor/Doctor.fxml"));
        createStage(loader);
    }
    
    public void showSalerWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Saler/Saler.fxml"));
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

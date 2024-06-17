/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Models;

import com.group4.petcenter.Controllers.AlertMessage;
import com.group4.petcenter.Controllers.ConnectDB;
import com.group4.petcenter.Views.ViewFactory;

/**
 *
 * @author Trần Quang Khải
 */
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final ConnectDB databaseDriver;
    private final AlertMessage alertMessage;
    
    // Admin
    
    
    // Staff
   

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new ConnectDB();
        this.alertMessage = new AlertMessage();
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }
    
    public ConnectDB getDB(){
        return databaseDriver;
    }
    
    public AlertMessage getAlertMessage(){
        return alertMessage;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class ConnectDB {
    Connection con = null;
    
    public Connection getConnect()
    {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PetCenter;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String pass = "123";
        
        try {
            //load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //connect to db
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Cannot connect");
        }
        
        return con;
    }
}

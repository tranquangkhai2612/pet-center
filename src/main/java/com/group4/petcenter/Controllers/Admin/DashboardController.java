/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.group4.petcenter.Controllers.Admin;

import com.group4.petcenter.Controllers.ConnectDB;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author vyvt
 */
public class DashboardController implements Initializable {
    private Connection connect;

    @FXML
    private AnchorPane addUser_form;
    @FXML
    private Label lb_totalincome;
    @FXML
    private Label lb_expiringsoon;
    @FXML
    private Label lb_lowstock;
    @FXML
    private PieChart pc_breed;
    @FXML
    private LineChart<String, Number> lc_totalhc;
    @FXML
    private BarChart<String, Number> bc_totalincome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardPcB();
        dashboardLbTI();
        dashboardBcTi();
        dashboardLcHr();
        dashboardLbLs();
        dashboardLbEs();
    }    
    
    public void dashboardPcB() {
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        String query = "SELECT Breed, COUNT(*) AS Count FROM Animals GROUP BY Breed;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            pc_breed.getData().clear();
            while(rs.next()){
                String breed = rs.getString("Breed");
                int count = rs.getInt("Count");
                pc_breed.getData().add(new PieChart.Data(breed, count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardLbTI() {
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        String query = "SELECT SUM(total) AS totalSum FROM Bill;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                BigDecimal totalIncome = rs.getBigDecimal("totalSum"); 
                int ti = totalIncome.intValue();
                lb_totalincome.setText(String.valueOf(ti));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardLbLs() {
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        String query = "select count(Products_id) as cnt from Products where Quantity < 20;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                BigDecimal totalIncome = rs.getBigDecimal("cnt"); 
                int ti = totalIncome.intValue();
                lb_lowstock.setText(String.valueOf(ti));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardLbEs() {
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        String query = "select count(Products_id) as cnt from Products where Expired_date <= DATEADD(MONTH, 6, GETDATE());";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                BigDecimal totalIncome = rs.getBigDecimal("cnt"); 
                int ti = totalIncome.intValue();
                lb_expiringsoon.setText(String.valueOf(ti));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardBcTi() {
        bc_totalincome.getData().clear();
        String query = "WITH cte AS (" +
        " SELECT DATEPART(YEAR, date) AS [Year],DATEPART(MONTH, date) AS [Month], SUM(total) AS total_sum" +
        " FROM Bill GROUP BY DATEPART(YEAR, date), DATEPART(MONTH, date) ORDER BY [Year] DESC, [Month] DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY )" +
        " SELECT * FROM cte ORDER BY [Year], [Month] ASC;";
        XYChart.Series chart = new XYChart.Series();      
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                chart.setName(rs.getString(1));
                chart.getData().add(new XYChart.Data(rs.getString(2), rs.getDouble(3)));
            }
            bc_totalincome.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardLcHr() {
        lc_totalhc.getData().clear();
        String query = "WITH cte AS (" +
        " SELECT DATEPART(YEAR, Record_date) AS [Year],DATEPART(MONTH, Record_date) AS [Month],COUNT(Health_record_id) AS total_sum" +
        " FROM Health_Record GROUP BY DATEPART(YEAR, Record_date), DATEPART(MONTH, Record_date) ORDER BY [Year] DESC, [Month] DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY)" +
        " SELECT * FROM cte ORDER BY [Year], [Month] ASC;";
        XYChart.Series chart = new XYChart.Series();      
        ConnectDB connect = new ConnectDB();
        Connection con = connect.getConnect();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                chart.setName(rs.getString(1));
                chart.getData().add(new XYChart.Data(rs.getString(2), rs.getDouble(3)));
            }
            lc_totalhc.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

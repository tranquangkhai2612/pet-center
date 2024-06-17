/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Controllers.ConnectDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class BillDAO {
    public List<Bill> getAllBills() {
        List<Bill> billList = new ArrayList<>();

        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Bill");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getString("Bill_id"));
                bill.setCustomerId(resultSet.getString("Customer_id"));
                bill.setDate(resultSet.getDate("Date"));
                bill.setHealthRecordId(resultSet.getString("Health_record_id"));
                bill.setNote(resultSet.getString("Note"));
                bill.setTotal(resultSet.getInt("Total"));
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billList;
    }
}

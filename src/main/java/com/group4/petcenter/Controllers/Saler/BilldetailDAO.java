package com.group4.petcenter.Controllers.Saler;

import com.group4.petcenter.Controllers.ConnectDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BilldetailDAO {

    public void addBillDetail(Billdetail billDetail) {
        String query = "INSERT INTO Bill_details (Bill_id, Product_id, Service_id, Quantity, Amount, Unit_price, Quantity_service, Price_service) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectDB().getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set Bill_id as String
            preparedStatement.setString(1, billDetail.getBillId().getBillId());

            // Set Product_id as String
            preparedStatement.setString(2, billDetail.getProductName());

            // Set Service_id as String
            preparedStatement.setString(3, billDetail.getServiceName());

            // Set Quantity as int
            preparedStatement.setInt(4, billDetail.getQuantity());

            // Set Amount as BigDecimal
            preparedStatement.setBigDecimal(5, billDetail.getAmount());

            // Set Unit_price as BigDecimal
            preparedStatement.setBigDecimal(6, billDetail.getUnitPrice());

            // Set Quantity_service as int
            preparedStatement.setInt(7, billDetail.getQuantityService());

            // Set Price_service as BigDecimal
            preparedStatement.setBigDecimal(8, billDetail.getPriceService());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    package com.group4.petcenter.Controllers.Saler;

    import com.group4.petcenter.Controllers.ConnectDB;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;

    import java.math.BigDecimal;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    public class ProductDAO {
        public ObservableList<Product> getAllProducts() {
            ObservableList<Product> productList = FXCollections.observableArrayList();
            String query = "SELECT * FROM Products";

            try (Connection connection = new ConnectDB().getConnect();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getString("Products_id"),
                            resultSet.getString("Name"),
                            resultSet.getInt("Quantity"),
                            resultSet.getDate("Expired_date").toString(),
                            resultSet.getInt("Price")
                    );
                    productList.add(product);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return productList;
        }
    }

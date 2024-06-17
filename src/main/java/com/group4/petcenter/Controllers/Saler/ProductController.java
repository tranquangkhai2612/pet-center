package com.group4.petcenter.Controllers.Saler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    public TableView<Product> tb_product_view;
    @FXML
    public TableColumn<Product, String> tb_product_id;
    @FXML
    public TableColumn<Product, String> tb_product_name;
    @FXML
    public TableColumn<Product, Double> tb_product_price;
    @FXML
    public TableColumn<Product, String> tb_product_date;
    @FXML
    public TableColumn<Product, Integer> tb_product_quantity;
    @FXML
    public TableColumn<Product, Void> tb_product_button;

    @FXML
    public TableView<Billdetail> tb_bill_detail_view;
    @FXML
    public TableColumn<Billdetail, String> tb_detail_bill_id;
    @FXML
    public TableColumn<Billdetail, String> tb_bill_detail_product_name;
    @FXML
    public TableColumn<Billdetail, String> tb_bill_detail_service_id;
  
    
    @FXML
    public TableColumn<Billdetail, BigDecimal> tb_bill_detail_amount;

    @FXML
    public TableView<Service> tb_service_view;
    @FXML
    public TableColumn<Service, String> tb_service_id;
    @FXML
    public TableColumn<Service, String> tb_service_name;
    @FXML
    public TableColumn<Service, Double> tb_service_price;
    @FXML
    public TableColumn<Service, Void> tb_service_action;

    @FXML
    public AnchorPane Bill;
    @FXML
    public Button btn_remove;
    @FXML
    public Button btn_pay;
    @FXML
    public Button btn_search;

    public ObservableList<Billdetail> billDetailList = FXCollections.observableArrayList();
    @FXML
    public TableColumn<Billdetail, Integer> tb_bill_detail_quantity_service;
    @FXML
    public TableColumn<Billdetail, BigDecimal> tb_bill_detail_service_price;
    @FXML
    public TableColumn<Billdetail, Integer> tb_bill_detail_quantity_product;
    @FXML
    public TableColumn<Billdetail, BigDecimal> tb_bill_detail_unit_price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up columns for product table
        tb_product_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tb_product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tb_product_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tb_product_date.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
        tb_product_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Set up columns for bill detail table
        tb_detail_bill_id.setCellValueFactory(new PropertyValueFactory<>("billId"));
                tb_bill_detail_service_id.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tb_bill_detail_quantity_service.setCellValueFactory(new PropertyValueFactory<>("quantityService"));
        tb_bill_detail_service_price.setCellValueFactory(new PropertyValueFactory<>("priceService"));
        tb_bill_detail_product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
         tb_bill_detail_quantity_product.setCellValueFactory(new PropertyValueFactory<>("quantity"));
         tb_bill_detail_unit_price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
         tb_bill_detail_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        // Set up columns for service table
        tb_service_id.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        tb_service_name.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tb_service_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add button column to product table
        tb_product_button.setCellFactory(new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(TableColumn<Product, Void> param) {
                return new TableCell<Product, Void>() {
                    public final Button button = new Button("Add");
                    public final TextField textField = new TextField();

                    {
                        textField.setPromptText("Quantity");
                        button.setOnAction(event -> {
                            Product product = getTableView().getItems().get(getIndex());
                            try {
                                int quantity = Integer.parseInt(textField.getText());
                                BigDecimal unitPrice = BigDecimal.valueOf(product.getPrice());
                                BigDecimal amount = unitPrice.multiply(BigDecimal.valueOf(quantity));

                                Billdetail billDetail = new Billdetail();
                                billDetail.setProductName(product.getName());
                                billDetail.setQuantity(quantity);
                                billDetail.setUnitPrice(unitPrice);
                                billDetail.setAmount(amount);

                                billDetailList.add(billDetail);
                                tb_bill_detail_view.setItems(billDetailList);

                            } catch (NumberFormatException e) {
                                System.err.println("Invalid quantity: " + textField.getText());
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            AnchorPane pane = new AnchorPane();
                            pane.getChildren().addAll(textField, button);
                            AnchorPane.setLeftAnchor(textField, 0.0);
                            AnchorPane.setRightAnchor(button, 0.0);
                            setGraphic(pane);
                        }
                    }
                };
            }
        });

//          tb_service_action.setCellFactory(new Callback<TableColumn<Service, Void>, TableCell<Service, Void>>() {
//        @Override
//        public TableCell<Service, Void> call(TableColumn<Service, Void> param) {
//            return new TableCell<Service, Void>() {
//                public final Button button = new Button("Add");
//                public final TextField textField = new TextField();
//
//                {
//                    textField.setPromptText("Số lượng");
//                    button.setOnAction(event -> {
//                        Service service = getTableView().getItems().get(getIndex());
//                        try {
//                            int quantity = Integer.parseInt(textField.getText());
//                            BigDecimal unitPrice = BigDecimal.valueOf(service.getPrice());
//                            BigDecimal amount = unitPrice.multiply(BigDecimal.valueOf(quantity));
//                            Billdetail billDetail = new Billdetail();
//                            billDetail.setServiceName(service.getServiceName());
//                            billDetail.setQuantityService(quantity);
//                            billDetail.setPriceService(unitPrice);
//                            billDetail.setAmount(amount);
//                            billDetailList.add(billDetail);
//                            tb_bill_detail_view.setItems(billDetailList);
//
//                        } catch (NumberFormatException e) {
//                            System.err.println("Số lượng không hợp lệ: " + textField.getText());
//                        }
//                    });
//                }
//
//                @Override
//                protected void updateItem(Void item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setGraphic(null);
//                    } else {
//                        AnchorPane pane = new AnchorPane();
//                        pane.getChildren().addAll(textField, button);
//                        AnchorPane.setLeftAnchor(textField, 0.0);
//                        AnchorPane.setRightAnchor(button, 0.0);
//                        setGraphic(pane);
//                    }
//                }
//            };
//        }
//    });

    // Tải dữ liệu ban đầu vào các bảng
    loadProductData();
    loadServiceData();

    // Xử lý sự kiện cho nút "Thanh toán"
    btn_pay.setOnAction(event -> saveBillDetails());
}

public void loadProductData() {
    ProductDAO productDAO = new ProductDAO();
    ObservableList<Product> productList = productDAO.getAllProducts();
    tb_product_view.setItems(productList);
}

public void loadServiceData() {
    ServiceDAO serviceDAO = new ServiceDAO();
    ObservableList<Service> serviceList = serviceDAO.getAllServices();
    tb_service_view.setItems(serviceList);
}

public void saveBillDetails() {
    BilldetailDAO billdetailDAO = new BilldetailDAO();
    for (Billdetail billDetail : billDetailList) {
        billdetailDAO.addBillDetail(billDetail);
    }
    billDetailList.clear();
    tb_bill_detail_view.setItems(billDetailList);
}
}

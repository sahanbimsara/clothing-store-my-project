package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrderFormController {

    @FXML
    private TableColumn<String[], String> ColDes;

    @FXML
    private Label Date;

    @FXML
    private Label NetTotal;

    @FXML
    private Label Time;

    @FXML
    private ComboBox<String> cmbCustomerID;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<String[], String> colItemCode;

    @FXML
    private TableColumn<String[], String> colQty;

    @FXML
    private TableColumn<String[], String> colTotal;

    @FXML
    private TableColumn<String[], String> colUnitPrice;

    @FXML
    private TableView<String[]> tblOrder;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCusomerName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    // Table data list
    private final ObservableList<String[]> cartList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Show Date and Time
        Date.setText("Date: " + LocalDate.now());
        Time.setText("Time: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // Configure TableView columns using String[] indexes
        colItemCode.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
        ColDes.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
        colQty.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));
        colUnitPrice.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[3]));
        colTotal.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[4]));

        tblOrder.setItems(cartList);

        // Load sample data
        loadCustomers();
        loadItems();

        cmbCustomerID.setOnAction(event ->{
            String selectedId = cmbCustomerID.getValue();
            loadCustomerDetails(selectedId);
        });
    }

    private void loadCustomers() {
        ObservableList<String> customerIds = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT id FROM Customers");

            while (resultSet.next()){
                customerIds.add(resultSet.getString("id"));
            }
            cmbCustomerID.setItems(customerIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadCustomerDetails(String id){
        try {
            ResultSet rs = CrudUtil.execute("SELECT name, address FROM Customers WHERE id=?", id);
            if (rs.next()){
                txtCusomerName.setText(rs.getString("name"));
                txtAddress.setText(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItems() {
        cmbItemCode.setItems(FXCollections.observableArrayList("I001", "I002", "I003"));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            String itemCode = cmbItemCode.getValue();
            String description = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double total = qty * unitPrice;

            // Store row as String[]
            String[] row = {
                    itemCode,
                    description,
                    String.valueOf(qty),
                    String.valueOf(unitPrice),
                    String.valueOf(total)
            };

            cartList.add(row);
            calculateNetTotal();
            clearItemFields();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid Quantity and Price!").show();
        }
    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {
        if (cartList.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No items in the cart!").show();
            return;
        }

        // Simulate placing the order
        new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully!").show();

        cartList.clear();
        calculateNetTotal();
    }

    // Calculate Net Total
    private void calculateNetTotal() {
        double netTotal = 0;
        for (String[] row : cartList) {
            netTotal += Double.parseDouble(row[4]);
        }
        NetTotal.setText("Net Total: " + netTotal);
    }

    // Clear Item Fields
    private void clearItemFields() {
        txtDescription.clear();
        txtStock.clear();
        txtQty.clear();
        txtUnitPrice.clear();
    }
}

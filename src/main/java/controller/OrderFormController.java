package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderFormController {

    @FXML
    private TableColumn<?, ?> ColDes;

    @FXML
    private Label Date;

    @FXML
    private Label NetTotal;

    @FXML
    private Label Time;

    @FXML
    private ComboBox<?> cmbCustomerID;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView  tblOrder;

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

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {

    }

}

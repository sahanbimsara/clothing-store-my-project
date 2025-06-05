package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import service.CustomerServiceImpl;
import service.CustomerService;
import util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormConroller {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPhone;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TableView txtTable;

    List<Customer> customerList = new ArrayList<>();


    @FXML         // ADD CUSTOMER TO TABLE & SEND TO MYSQL DATABASE  => CrudUtil package
    void btnAddOnAction(ActionEvent event) {
        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customers");

            PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("Insert Into customers VALUES (?,?,?,?)");

            psTm.setString(1,txtId.getText());
            psTm.setString(2,txtName.getText());
            psTm.setString(3,txtAddress.getText());
            psTm.setString(4,txtPhone.getText());


            if (psTm.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added!!!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        // RELOAD TABLE DATA

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();

    }

        //LOAD DATA TO TABLE & SHOW DATA IN TABLE => CrudUtil package

    private void loadTable(){

        try {

            CustomerService service = new CustomerServiceImpl();
            List<Customer> all = service.getAll();


            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
            all.forEach(customer -> {
                customerObservableList.add(customer);
            });
            txtTable.setItems(customerObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

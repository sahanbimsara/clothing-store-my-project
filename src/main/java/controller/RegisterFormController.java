package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event){

    //    String key = "#1234";

    //    BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

    //    basicTextEncryptor.setPassword(key);


        String SQL = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {

            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ResultSet resultSet = null;
            try {
                resultSet = connection.createStatement().executeQuery(
                        "SELECT * FROM users WHERE email = '" + txtEmail.getText() + "'"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (!resultSet.next()) {

                   User user = new User(
                            txtUserName.getText(),
                            txtEmail.getText(),
                           txtPassword.getText()
                    );

                    PreparedStatement psTm = connection.prepareStatement(SQL);
                    psTm.setString(1, user.getUserName());
                    psTm.setString(2, user.getEmail());
                    psTm.setString(3, user.getPassword());

                    if (psTm.executeUpdate() > 0) {
                        new Alert(Alert.AlertType.INFORMATION, "User Registered Successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Registration Failed!").show();
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "User with this email already exists.").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Passwords do not match!").show();
        }
    }

}

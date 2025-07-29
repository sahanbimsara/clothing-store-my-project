package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;




    //Navigate user to dashboard after filling username and passowrd

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException, IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter username and password!").show();
            return;
        }

        String SQL = "SELECT * FROM users WHERE username = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, username);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            if (user.getPassword().equals(password)) {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBoard.fxml"))));
                stage.show();

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect password!").show();
            }

            System.out.println(user);
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found!").show();
        }

        resultSet.close();
        stmt.close();
    }

    @FXML
    void hyperRegisterOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;



public class DashBoardFormController {

    @FXML
    public AnchorPane loadPane;

    @FXML
    void btnTest(ActionEvent event) throws IOException {
        URL recource = this.getClass().getResource("/view/registerForm.fxml");

        assert recource != null;

        Parent load = FXMLLoader.load(recource);

        loadPane.getChildren().clear();
        loadPane.getChildren().add(load);

    }
        @FXML
        void btnlog(ActionEvent event) throws IOException {

            URL recource = this.getClass().getResource("/view/loginForm.fxml");

            assert recource != null;

            Parent load = FXMLLoader.load(recource);

            loadPane.getChildren().clear();
            loadPane.getChildren().add(load);

        }



}

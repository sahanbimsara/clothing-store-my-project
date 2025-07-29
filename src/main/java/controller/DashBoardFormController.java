package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;


public class DashBoardFormController implements Initializable {

    @FXML
    public AnchorPane loadPane;

    @FXML
    public Label lblDate;

    @FXML
    public Label lblTime;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        URL recource = this.getClass().getResource("/view/registerForm.fxml");

        assert recource != null;

        Parent load = FXMLLoader.load(recource);

        loadPane.getChildren().clear();
        loadPane.getChildren().add(load);

    }


    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {

        URL recource = this.getClass().getResource("/view/customerDetails.fxml");

        assert recource != null;

        Parent load = FXMLLoader.load(recource);

        loadPane.getChildren().clear();
        loadPane.getChildren().add(load);

    }

    private  void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String format = dateFormat.format(date);
        lblDate.setText(format);

        System.out.println(LocalDate.now());

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }


    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL recource = this.getClass().getResource("/view/orderForm.fxml");

        assert recource != null;

        Parent load = FXMLLoader.load(recource);

        loadPane.getChildren().clear();
        loadPane.getChildren().add(load);




    }

    public void btnItemsOnAction(ActionEvent actionEvent) {
    }
}

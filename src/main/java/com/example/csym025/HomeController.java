package com.example.csym025;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label customer;
    @FXML
    private Label property;

    @FXML
    private Label rentScene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customer-screen.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene customerScene = new Scene(root);
                stage.setScene(customerScene);
                stage.setTitle("Customer Manager");
                stage.show();
                stage.setResizable(false);
                customer.getScene().getWindow().hide();

              /*  customer.setText("Properties");*/
            }
        });


        property.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("property-screen.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene customerScene = new Scene(root);
                stage.setScene(customerScene);
                stage.setTitle("Property Manager");
                stage.show();
                stage.setResizable(false);
                property.getScene().getWindow().hide();

                /*  customer.setText("Properties");*/
            }
        });

        rentScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("rent-screen.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene customerScene = new Scene(root);
                stage.setScene(customerScene);
                stage.setTitle("Rent Manager");
                stage.show();
                stage.setResizable(false);
                rentScene.getScene().getWindow().hide();

                /*  customer.setText("Properties");*/
            }
        });
    }
}
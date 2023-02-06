package com.example.csym025;

import com.example.csym025.main.customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class customerClass implements Initializable {

    @FXML
    private Button addCustomer;

    @FXML
    private Button editCustomers;


    @FXML
    private TableView<customerDataClass> customerTable;

    @FXML
    private Button back;

    @FXML
    private TableColumn<customerDataClass, String> email;

    @FXML
    private TextField enterEmail;

    @FXML
    private Button saveData;
    @FXML
    private TextField serachCustomer;

    @FXML
    private TextField enterId;

    @FXML
    private TextField enterName;

    @FXML
    private TextField enterPhone;

    @FXML
    private TableColumn<customerDataClass, String> id;

    @FXML
    private Button importCustomer;

    @FXML
    private Button exportCustomer;


    @FXML
    private TableColumn<customerDataClass, String> name;

    @FXML
    private TableColumn<customerDataClass, String> phone;

    @FXML
    private Button removeCustomer;

    @FXML
    private Button updateCustomer;


    //this list is attched to the table view and it updates the table as its values change
    ObservableList<customerDataClass> customerList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTable.setItems(customerList);

        customer.importData("./dataBase.csv.",customerList);
        linkColumnToTable();


        addCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customer.addTwoLists(customerList,getDataFromTextFields());
               linkColumnToTable();

            }
        });

        removeCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customerTable.getItems().removeAll(customerTable.getSelectionModel().getSelectedItem());
            }
        });

        editCustomers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getData();
            }
        });

        updateCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customer.addTwoLists(customerList,getDataFromTextFields());
                customerTable.getItems().removeAll(customerTable.getSelectionModel().getSelectedItem());
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene homeScene = new Scene(root);
                stage.setScene(homeScene);
                stage.setTitle("Home");
                stage.show();
                stage.setResizable(false);
                back.getScene().getWindow().hide();
            }
        });

        importCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customer.importData("./customerdetails.csv",customerList);
                linkColumnToTable();
            }
        });

        exportCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    customer.exportData("./ExportedCustomer.csv.",customerList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        saveData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> customerDetailsArray = new ArrayList<String>();

                for (customerDataClass customerDetails : customerTable.getItems()) {
                    String text = customerDetails.getId() + "," + customerDetails.getName() + "," + customerDetails.getPhone() + "," + customerDetails.getEmail() + "\n";
                    customerDetailsArray.add(text);
                }
                try {
                    customer.saveToDataBase(customerDetailsArray,"./customerDatabase.lekan");
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        FilteredList<customerDataClass> filteredList = new FilteredList<>(customerList, b -> true);
        serachCustomer.textProperty().addListener((observable, oldDAta, newData) ->{
            filteredList.setPredicate(customersData -> {
                if (newData == null || newData.isEmpty()){

                    return true;
                }
                String lowerCaseFilter = newData.toLowerCase();
                if(customersData.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if(customersData.getEmail().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getPhone().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                else return customersData.getId().toLowerCase().contains(lowerCaseFilter);
            });
            SortedList<customerDataClass> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(customerTable.comparatorProperty());
            customerTable.setItems(sortedList);

        });
    }



    //this method gets data inputted into the text fields and fill them into the table
    public customerDataClass getDataFromTextFields(){

        String id =enterId.getText();
        String name = enterName.getText();
        String email = enterEmail.getText();
        String phone = enterPhone.getText();

        customerDataClass listFromTextFields = new customerDataClass(id, name,phone, email);

        enterId.clear();
        enterName.clear();
        enterEmail.clear();
        enterPhone.clear();

        return listFromTextFields;
    }
    public void getData(){
        enterId.setText(customerTable.getSelectionModel().getSelectedItem().getId());
        enterName.setText(customerTable.getSelectionModel().getSelectedItem().getName());
        enterEmail.setText(customerTable.getSelectionModel().getSelectedItem().getEmail());
        enterPhone.setText(customerTable.getSelectionModel().getSelectedItem().getPhone());
    }

    //this method links each column on then table using its id
    public void linkColumnToTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

    }



}
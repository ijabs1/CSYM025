package com.example.csym025;

import com.example.csym025.main.property;
import com.example.csym025.main.rentProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

public class rentClass implements Initializable {
    @FXML
    private TableColumn<rentDataClass, String> EndofTenancy;

    @FXML
    private TableColumn<rentDataClass, String> RentedDate;

    @FXML
    private TableColumn<rentDataClass, String> cusID;

    @FXML
    private TableColumn<rentDataClass, String> custEmail;

    @FXML
    private TableColumn<rentDataClass, String> custName;

    @FXML
    private TableColumn<rentDataClass, String> custPhone;

    @FXML
    private TextField customerEmail;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerPhone;

    @FXML
    private TextField customerid;

    @FXML
    private TextField endOfTenancy;

    @FXML
    private TableColumn<rentDataClass, String> propId;

    @FXML
    private TableColumn<rentDataClass, String> propPostCode;
    @FXML
    private TextField damages;
    @FXML
    private Button generateInvoice;
    @FXML
    private TableColumn<rentDataClass, String> propType;

    @FXML
    private TextField propertyPostCode;

    @FXML
    private TextField propertyType;

    @FXML
    private TextField propertyid;

    @FXML
    private Button remove;

    @FXML
    private Button rent;
    @FXML
    private Button back;

    @FXML
    private TextField rentDate;

    @FXML
    private Button save;

    @FXML
    private TableView<rentDataClass> table;
    ObservableList<rentDataClass> rentList = FXCollections.observableArrayList();
    ArrayList<String> rentArray = new ArrayList<String>();
    ObservableList<propertySpecsDataClass> propertySpecsList = FXCollections.observableArrayList();
    ObservableList<customerDataClass> custmerList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setItems(rentList);
        cusID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        propId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        propType.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        propPostCode.setCellValueFactory(new PropertyValueFactory<>("propertyPostCode"));
        RentedDate.setCellValueFactory(new PropertyValueFactory<>("rentedDate"));
        EndofTenancy.setCellValueFactory(new PropertyValueFactory<>("endOfTenancy"));

        rentProperty.importData("./rentDataBase.csv.",rentList);
        rentProperty.importpropertySpecs("./propertySpecsDataBase.csv.",propertySpecsList);
        rentProperty.importsavedCustomerData("./dataBase.csv.",custmerList);
        rent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String customerId = customerid.getText();
                String customername = customerName.getText();
                String customerphone = customerPhone.getText();
                String customeremail = customerEmail.getText();
                String propertyId = propertyid.getText();
                String propertytype = propertyType.getText();
                String propertypostcode = propertyPostCode.getText();
                String rentedate = rentDate.getText();
                String endoftenancy =endOfTenancy.getText();

                Integer traceIndex = Integer.valueOf(propertyId)-1;
                propertySpecsList.get(traceIndex).setAvailable("n");
                saveToPRopertySpecsDatabase();
                rentDataClass addedlist = new rentDataClass(customerId,customername,customerphone,customeremail,propertyId,propertytype,propertypostcode,rentedate,endoftenancy);
                rentList.addAll(addedlist);
                customerid.clear();
                customerName.clear();
                customerPhone.clear();
                customerEmail.clear();
                propertyid.clear();
                propertyType.clear();
                propertyPostCode.clear();
                rentDate.clear();
                endOfTenancy.clear();
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (rentDataClass exportCustomerData : table.getItems()) {
                    String text = exportCustomerData.getCustomerId() + "," + exportCustomerData.getCustomerName() + "," + exportCustomerData.getCustomerPhone() + "," + exportCustomerData.getCustomerEmail()  + "," + exportCustomerData.getPropertyId()  + "," + exportCustomerData.getPropertyType() + "," + exportCustomerData.getPropertyPostCode() + "," + exportCustomerData.getRentedDate() + "," + exportCustomerData.getEndOfTenancy()  + "\n";
                    rentArray.add(text);
                }
                try {
                    rentProperty.saveToDataRentBase(rentArray,"./rentDatabase.lekan");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        generateInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    generateInvoice();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            }
        });
        customerid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer traceIndex = Integer.valueOf(customerid.getText())-1;
                String name = custmerList.get(traceIndex).getName();
                String phone = custmerList.get(traceIndex).getPhone();
                String email = custmerList.get(traceIndex).getEmail();
                customerName.setText(name);
                customerPhone.setText(phone);
                customerEmail.setText(email);
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
        propertyid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer traceIndex = Integer.valueOf(propertyid.getText())-1;
                String postCode = propertySpecsList.get(traceIndex).getPost();
                String type = propertySpecsList.get(traceIndex).getType();
                propertyType.setText(type);
                propertyPostCode.setText(postCode);
            }
        });
    }

    //generates invoice as a text file

    //Invoice date
    //Invoice Number
    // Bill to :
    // customer name
    // rented date      End of tenenancy
    //property description
    //Agent fee
    //deposit fee
    //damages
    //Total
    public void generateInvoice() throws IOException {
        String invoiceNumber = rentProperty.generateInvoiceNumber() + "#";
        String invoiceDate = rentProperty.getCurrentDate();
        String customerName = table.getSelectionModel().getSelectedItem().getCustomerName();
        String customerPhone =table.getSelectionModel().getSelectedItem().getCustomerPhone();
        String customerEmail = table.getSelectionModel().getSelectedItem().getCustomerEmail();
        String rentedDate = table.getSelectionModel().getSelectedItem().getRentedDate();
        String endOfTenency = table.getSelectionModel().getSelectedItem().getEndOfTenancy();
        String propertyDescription = table.getSelectionModel().getSelectedItem().getPropertyType();
        Integer traceIndex = Integer.valueOf(table.getSelectionModel().getSelectedItem().getPropertyId())-1;
        double rentPCM=  Double.parseDouble(propertySpecsList.get(traceIndex).getRent());
        double agentFee = rentPCM *0.2;
        double deposit = rentPCM*6;
        double damagesFee;
        if(damages.getText() == ""){
            damagesFee = 0;
        }
        else{
            damagesFee = Double.parseDouble(damages.getText());
        }
        double total = damagesFee+agentFee+deposit;

        rentProperty.invoiceGenerator("./Invoice.txt",deposit,invoiceNumber,invoiceDate,customerName,customerPhone,customerEmail,rentedDate,endOfTenency,propertyDescription,agentFee,damagesFee,total);
    }


    //gets data from list, adds it to an arraylist and serialize it unto a file
    private void saveToPRopertySpecsDatabase() {
        ArrayList<String> customerDetailsArray = new ArrayList<String>();

        for (propertySpecsDataClass exportCustomerData : propertySpecsList) {
            String text = exportCustomerData.getId() + "," + exportCustomerData.getListed() + "," + exportCustomerData.getBedroom() + "," + exportCustomerData.getBathroom()  + "," + exportCustomerData.getRent()  + "," + exportCustomerData.getSize() + "," + exportCustomerData.getPost() + "," + exportCustomerData.getLatitude() + "," + exportCustomerData.getLongitude()  + "," + exportCustomerData.getFurnish() + "," + exportCustomerData.getType() + "," + exportCustomerData.getGarden()  + "," + exportCustomerData.getAvailable()  + "\n";
            customerDetailsArray.add(text);
        }
        try {
            property.saveToDataBase(customerDetailsArray,"./propertySpecsDatabase.lekan");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

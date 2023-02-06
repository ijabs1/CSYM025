package com.example.csym025;

import com.example.csym025.main.property;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class propertySpecsClass implements Initializable {

    @FXML
    private Button addSpecs;

    @FXML
    private TableColumn<?, ?> availability;

    @FXML
    private Button back;

    @FXML
    private TableColumn<propertySpecsDataClass, String> bathrooms;

    @FXML
    private TableColumn<propertySpecsDataClass, String> bedrooms;

    @FXML
    private Button editSpecs;

    @FXML
    private Button GetDIstance;
    @FXML
    private TextField enterAvailable;

    @FXML
    private TextField enterBathroom;

    @FXML
    private TextField enterBedrooms;

    @FXML
    private TextField enterFurnish;

    @FXML
    private TextField enterGarden;

    @FXML
    private TextField enterId;

    @FXML
    private TextField enterLatitude;

    @FXML
    private TextField enterListed;

    @FXML
    private TextField enterLongitude;

    @FXML
    private TextField enterPostcode;

    @FXML
    private TextField enterRent;

    @FXML
    private TextField enterSize;

    @FXML
    private TextField enterType;

    @FXML
    private Button exportSpecs;

    @FXML
    private TableColumn<propertySpecsDataClass, String> furnishing;

    @FXML
    private TableColumn<propertySpecsDataClass, String> garden;

    @FXML
    private TableColumn<propertySpecsDataClass, String> id;

    @FXML
    private Button importSpecs;

    @FXML
    private TableColumn<propertySpecsDataClass, String> latitude;

    @FXML
    private TableColumn<propertySpecsDataClass, String> listed;

    @FXML
    private TableColumn<propertySpecsDataClass, String> longitude;

    @FXML
    private TableColumn<propertySpecsDataClass, String> postcode;

    @FXML
    private TableView<propertySpecsDataClass> propertiesTable;

    @FXML
    private Button removeSpecs;

    @FXML
    private TableColumn<propertySpecsDataClass, String> rent;

    @FXML
    private Button saveData;

    @FXML
    private TextField serachCustomer;

    @FXML
    private TableColumn<propertySpecsDataClass, String> size;

    @FXML
    private TableColumn<propertySpecsDataClass, String> type;

    @FXML
    private Button updateSpecs;
    @FXML
    private Label aldiSupermarket;

    @FXML
    private Label asdaSupermarket;

    @FXML
    private Label busStation;

    @FXML
    private Label hospital;

    @FXML
    private Label lidlSupermarket;

    @FXML
    private Label morrisonSupermarket;

    @FXML
    private Label sainsburySupermarket;

    @FXML
    private Label tescoSupermarket;

    @FXML
    private Label train;

    @FXML
    private Label university;

    ObservableList<propertySpecsDataClass> propertySpecsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        propertiesTable.setItems(propertySpecsList);

        property.importData("./propertySpecsDataBase.csv.",propertySpecsList);
        linkColumnToTable();

        addSpecs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                property.addlists(propertySpecsList,getDataFromTextFields());
                linkColumnToTable();
            }
        });


        removeSpecs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                propertiesTable.getItems().removeAll(propertiesTable.getSelectionModel().getSelectedItem());
            }
        });

        editSpecs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getData();
            }
        });
        updateSpecs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                property.addlists(propertySpecsList,getDataFromTextFields());
                propertiesTable.getItems().removeAll(propertiesTable.getSelectionModel().getSelectedItem());
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
        importSpecs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                property.importData("./House_Rent_Dataset.csv",propertySpecsList);
                linkColumnToTable();
            }
        });
        exportSpecs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    property.exportData(propertySpecsList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GetDIstance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getDistance();
            }
        });
        saveData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> customerDetailsArray = new ArrayList<String>();

                for (propertySpecsDataClass exportCustomerData : propertiesTable.getItems()) {
                    String text = exportCustomerData.getId() + "," + exportCustomerData.getListed() + "," + exportCustomerData.getBedroom() + "," + exportCustomerData.getBathroom()  + "," + exportCustomerData.getRent()  + "," + exportCustomerData.getSize() + "," + exportCustomerData.getPost() + "," + exportCustomerData.getLatitude() + "," + exportCustomerData.getLongitude()  + "," + exportCustomerData.getFurnish() + "," + exportCustomerData.getType() + "," + exportCustomerData.getGarden()  + "," + exportCustomerData.getAvailable()  + "\n";
                    customerDetailsArray.add(text);
                }
                try {
                    property.saveToDataBase(customerDetailsArray,"./propertySpecsDatabase.lekan");
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        FilteredList<propertySpecsDataClass> filteredList = new FilteredList<>(propertySpecsList, b -> true);
        serachCustomer.textProperty().addListener((observable, oldDAta, newData) ->{
            filteredList.setPredicate(customersData -> {
                if (newData == null || newData.isEmpty()){

                    return true;
                }
                String lowerCaseFilter = newData.toLowerCase();
                if(customersData.getBathroom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if(customersData.getBedroom().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getListed().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getLongitude().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getLatitude().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getType().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getFurnish().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getPost().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getSize().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getRent().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getGarden().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(customersData.getAvailable().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }


                else return customersData.getId().toLowerCase().contains(lowerCaseFilter);
            });
            SortedList<propertySpecsDataClass> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(propertiesTable.comparatorProperty());
            propertiesTable.setItems(sortedList);

        });
    }

    //Gets data from tableview into textfields
    public void getData(){
        enterId.setText(propertiesTable.getSelectionModel().getSelectedItem().getId());
        enterListed.setText(propertiesTable.getSelectionModel().getSelectedItem().getListed());
        enterBedrooms.setText(propertiesTable.getSelectionModel().getSelectedItem().getBedroom());
        enterBathroom.setText(propertiesTable.getSelectionModel().getSelectedItem().getBathroom());
        enterRent.setText(propertiesTable.getSelectionModel().getSelectedItem().getRent());
        enterSize.setText(propertiesTable.getSelectionModel().getSelectedItem().getSize());
        enterPostcode.setText(propertiesTable.getSelectionModel().getSelectedItem().getPost());
        enterLatitude.setText(propertiesTable.getSelectionModel().getSelectedItem().getLatitude());
        enterLongitude.setText(propertiesTable.getSelectionModel().getSelectedItem().getLongitude());
        enterFurnish.setText(propertiesTable.getSelectionModel().getSelectedItem().getFurnish());
        enterType.setText(propertiesTable.getSelectionModel().getSelectedItem().getType());
        enterGarden.setText(propertiesTable.getSelectionModel().getSelectedItem().getGarden());
        enterAvailable.setText(propertiesTable.getSelectionModel().getSelectedItem().getAvailable());

    }

    private propertySpecsDataClass getDataFromTextFields() {

        String id =enterId.getText();
        String listed = enterListed.getText();
        String bedroom = enterBedrooms.getText();
        String bathroom = enterBathroom.getText();
        String rent =enterRent.getText();
        String size = enterSize.getText();
        String post = enterPostcode.getText();
        String latitude = enterLatitude.getText();
        String longitude =enterLongitude.getText();
        String furnish = enterFurnish.getText();
        String type = enterType.getText();
        String garden = enterGarden.getText();
        String available =enterAvailable.getText();

        propertySpecsDataClass addedLIst = new propertySpecsDataClass(id,listed,bedroom,bathroom,rent,size,post,latitude,longitude,furnish,type,garden,available);

        enterId.clear();
        enterListed.clear();
        enterBedrooms.clear();
        enterBathroom.clear();
        enterRent.clear();
        enterSize.clear();
        enterPostcode.clear();
        enterLatitude.clear();
        enterLongitude.clear();
        enterFurnish.clear();
        enterType.clear();
        enterGarden.clear();
        enterAvailable.clear();

        return addedLIst;
    }



    private void linkColumnToTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        listed.setCellValueFactory(new PropertyValueFactory<>("listed"));
        bedrooms.setCellValueFactory(new PropertyValueFactory<>("bedroom"));
        bathrooms.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
        rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        postcode.setCellValueFactory(new PropertyValueFactory<>("post"));
        latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        furnishing.setCellValueFactory(new PropertyValueFactory<>("furnish"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        garden.setCellValueFactory(new PropertyValueFactory<>("garden"));
        availability.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    private void getDistance(){
        train.setText("Train Station - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.23710,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.90631) + "miles");
        busStation.setText("Bus Station - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.23895,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.89801) + "miles");
        hospital.setText("Hospital - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.23607,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.88383) + "miles");
        sainsburySupermarket.setText("Sainsbury Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.23994,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.93415) + "miles");
        tescoSupermarket.setText("Tesco Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.21508,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.91129) + "miles");
        morrisonSupermarket.setText("Morrisons Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.23363,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.89477) + "miles");
        asdaSupermarket.setText("Asda Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.26037,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.90076) + "miles");
        lidlSupermarket.setText("Lidl Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.25551,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.83617) + "miles");
        aldiSupermarket.setText("Aldi Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.24021,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.92718) + "miles");
        university.setText("University Supermarket - " + property.distance(Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLatitude()),52.23161,Double.parseDouble(propertiesTable.getSelectionModel().getSelectedItem().getLongitude()),-0.89018) + "miles");

    }
}

package com.example.csym025.main;

import com.example.csym025.customerDataClass;
import com.example.csym025.propertySpecsDataClass;
import com.example.csym025.rentDataClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class rentPropertyTest {
    ArrayList<String> myArray = new ArrayList<String>();
    ObservableList<customerDataClass> addHeaders = FXCollections.observableArrayList();
    ObservableList<rentDataClass> propertynewList =  FXCollections.observableArrayList();

    ObservableList<propertySpecsDataClass> propertyList = FXCollections.observableArrayList();
    //Test log
    private static System.Logger logger= System.getLogger("propertyTestClassLog");

    @Test
    void generateInvoiceNumber() {
        String number = String.valueOf(rentProperty.generateInvoiceNumber());
        int actual = number.length();
        int expected = 4;
        if(actual == expected){
            logger.log(System.Logger.Level.INFO,"generateInvoice test pass" );

        }
        else{
            logger.log(System.Logger.Level.INFO,"generateInvoice test fail" );

        }
        assertEquals(expected,actual);
    }

    @Test
    void getCurrentDate() {
        String data = rentProperty.getCurrentDate();
        int actual = data.length();
        int expected = 10;
        if(actual == expected){
            logger.log(System.Logger.Level.INFO,"getCurrentDate test pass" );

        }
        else{
            logger.log(System.Logger.Level.INFO,"getCurrentData test fail" );

        }
        assertEquals(expected,actual);
    }

    @Test
    void saveToDataRentBase() {
        assertDoesNotThrow(() -> rentProperty.saveToDataRentBase(myArray,"./rentDatabase.test"));
    }


    @Test
    void importsavedCustomerData() {
        assertDoesNotThrow(() -> rentProperty.importsavedCustomerData("./House_Rent_Dataset.csv",addHeaders));
    }

    @Test
    void importpropertySpecs() {
        assertDoesNotThrow(() -> rentProperty.importpropertySpecs("./House_Rent_Dataset.csv",propertyList));

    }

    @Test
    void importData() {

        assertDoesNotThrow(() -> rentProperty.importData("./House_Rent_Dataset.csv",propertynewList));

    }

    @Test
    void invoiceGenerator() {
        assertDoesNotThrow(() -> rentProperty.invoiceGenerator("./testInvoice.csv",2,"tets","test" , "test","test","test","tets","test","test",0,0,0));

    }
}
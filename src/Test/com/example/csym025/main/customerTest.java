package com.example.csym025.main;

import com.example.csym025.customerDataClass;
import com.example.csym025.propertySpecsDataClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class customerTest {
    //Test log
    private static System.Logger logger= System.getLogger("customerTestClassLog");

    private customerDataClass newlist;
    ObservableList<customerDataClass> mylist = FXCollections.observableArrayList();
     ArrayList<String> myArray = new ArrayList<String>();

    @Before
    public void setUp(){
        newlist = new customerDataClass("id","list"," bedroom"
                , "test");
    }
    @Test
    void addTwoLists() {
        customer.addTwoLists(mylist,newlist);
        Integer theActual = mylist.size();
        Integer theExpected = 1;
        if(theActual == theExpected){
            logger.log(System.Logger.Level.INFO,"addTwoLists test pass" );

        }
        else{
            logger.log(System.Logger.Level.INFO,"addTwoLists test fail" );

        }

        assertEquals(theExpected,theActual);
    }

    @Test
    void importData() {
        assertDoesNotThrow(() -> customer.importData("./test.csv",mylist));
    }

    @Test
    void exportData() {
        assertDoesNotThrow(() -> customer.exportData("./testExport.csv",mylist));
    }

    @Test
    void saveToDataBase() {
        assertDoesNotThrow(() -> customer.saveToDataBase(myArray,"./testpropertydatabase.lekan"));
    }

    @Test
    void readCustomer() {
        assertDoesNotThrow(() -> customer.readCustomer("./testpropertydatabase.lekan","./test.csv"));
    }
}
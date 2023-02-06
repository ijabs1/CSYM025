package com.example.csym025.main;

import com.example.csym025.propertySpecsDataClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class propertyTest {
    //Test log
    private static System.Logger logger= System.getLogger("propertyTestClassLog");

    private propertySpecsDataClass newlist;
    ObservableList<propertySpecsDataClass> mylist = FXCollections.observableArrayList();
    ObservableList<propertySpecsDataClass> addHeaders = FXCollections.observableArrayList();
    ArrayList<String> myArray = new ArrayList<String>();

    @Before
    public void setUp(){
        newlist = new propertySpecsDataClass("id","list"," bedroom"
                , "bathroom", "rent", "size", "post",
                "latitude","furniture",  "type", "garden"
                , "available","test");
    }

    @Test
    void importData() {
        assertDoesNotThrow(() -> property.importData("./House_Rent_Dataset.csv",mylist));
    }

    @Test
    void exportData() {
        assertDoesNotThrow(() -> property.exportData(mylist));

    }
    @Test
    void addlists() {
        property.addlists(mylist,newlist);
        Integer theActual = mylist.size();
        Integer theExpected = 1;
        if(theActual.equals(theExpected)){
            logger.log(System.Logger.Level.INFO,"addLists test pass" );

        }
        else{
            logger.log(System.Logger.Level.INFO,"addLists test fail" );

        }
        assertEquals(theExpected,theActual);
    }

    @Test
    void saveToDataBase() {
        assertDoesNotThrow(() -> property.saveToDataBase(myArray,"./testpropertydatabase.lekan"));
    }

    @Test
    void readCustomer() {
        assertDoesNotThrow(() -> property.readCustomer());

    }
}
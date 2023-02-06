package com.example.csym025.main;

import com.example.csym025.customerDataClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class customer {

    //Test log
    private static System.Logger logger= System.getLogger("customerClassLog");

    //takes two list parameters and adds them together
    public static void addTwoLists( ObservableList<customerDataClass> customerList, customerDataClass listFromTextFields){
        logger.log(System.Logger.Level.INFO,"Starting addTwoLists method" );
        customerList.addAll(listFromTextFields);
    }

    //takes list and a string as parameter, the method imports the list parameter from the String filePath parameter
    public static void importData(String filepath, ObservableList<customerDataClass> customerList) {
        logger.log(System.Logger.Level.INFO,"Starting importData method" );

        String splitParameter = ",";
        BufferedReader buffer;

        try {
            buffer = new BufferedReader(new FileReader(filepath));
            String newLine;
            while ((newLine = buffer.readLine()) != null) {
                String[] columnFields = newLine.split(splitParameter, -1);
                customerDataClass importedList = new customerDataClass(columnFields[0], columnFields[1], columnFields[2], columnFields[3]);
                customerList.add(importedList);
            }
        } catch (FileNotFoundException ex) {
            logger.log(System.Logger.Level.ERROR,"File to be imported Not found" );

            System.out.println("File to be imported Not found");
        } catch (IOException ex) {
            logger.log(System.Logger.Level.ERROR,"Error reading imported file" );

            System.out.println("Error reading imported file");
        }
    }

    //takes data from the observable list parameter and export in a csv format to the String exportPath parameter
    public static void exportData(String exportPath,ObservableList<customerDataClass> customerList) throws IOException {
        logger.log(System.Logger.Level.INFO,"Starting exportData method" );

        ObservableList<customerDataClass> exportList = FXCollections.observableArrayList();
        exportList.addAll(customerList);
        Writer writer = null;
        try {
            File file = new File(exportPath);
            writer = new BufferedWriter(new FileWriter(file));
            for (customerDataClass exportCustomerData : exportList) {
                String text = exportCustomerData.getId() + "," + exportCustomerData.getName() + "," + exportCustomerData.getPhone() + "," + exportCustomerData.getEmail() + "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            logger.log(System.Logger.Level.ERROR,"Error writing to file" );

            ex.printStackTrace();
        }
        finally {

            assert writer != null;
            writer.flush();
            writer.close();
        }
    }

    //takes Arraylist and String as parameter. The method serializes the arraylist to the String serializationPath
    public static void saveToDataBase(ArrayList<String> customerDetailsArray, String serializationPath) throws IOException, ClassNotFoundException {
        logger.log(System.Logger.Level.INFO,"Starting saveToDataBase method" );

        try {
            FileOutputStream fos_lekan = new FileOutputStream(serializationPath);
            // write object to file
            ObjectOutputStream oos_lekan = new ObjectOutputStream(fos_lekan);
            oos_lekan.writeObject(customerDetailsArray);
            fos_lekan.close();
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR,"Error during serialization" );
            e.printStackTrace();
        }

        readCustomer("./customerDatabase.lekan","./dataBase.csv.");
    }

    //takes the path of the serialized file as a parameter and generates a csv file to the csvPath
    public static void readCustomer(String serializePath, String csvPath) throws IOException, ClassNotFoundException {
        logger.log(System.Logger.Level.INFO,"Starting readCustomer method" );

        List database;
        FileInputStream fis = new FileInputStream(serializePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        database = (ArrayList) ois.readObject();
        ois.close();
        fis.close();
        String databaseString = database.toString();

        StringBuilder sb = new StringBuilder(databaseString);
        sb.deleteCharAt(databaseString.length() -1);
        sb.deleteCharAt(0);
        databaseString = sb.toString();
        String spaceComma = "," +" ";
        databaseString =databaseString.replace(spaceComma,"");


        Writer writer = null;
        try {
            File file = new File(csvPath);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(databaseString);
        } catch (Exception ex) {
            logger.log(System.Logger.Level.ERROR,"Error deserializing and writing to csv" );
            ex.printStackTrace();
        } finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }
}

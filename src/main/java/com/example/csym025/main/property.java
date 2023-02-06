package com.example.csym025.main;

import com.example.csym025.propertySpecsDataClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class property {


    //Test log
    private static System.Logger logger= System.getLogger("propertyClassLog");

    //adds new data into propertySpecsList
    public static void addlists( ObservableList<propertySpecsDataClass> propertySpecsList,propertySpecsDataClass addedLIst){
        logger.log(System.Logger.Level.INFO,"Starting addLists method" );

        propertySpecsList.addAll(addedLIst);
    }
    //imports csv file unto tableview
    public static void importData(String s, ObservableList<propertySpecsDataClass> propertySpecsList) {
        logger.log(System.Logger.Level.INFO,"Starting importData method" );

        String splitParameter = ",";
        BufferedReader buffer;

        try{
            buffer = new BufferedReader(new FileReader(s));
            String newLine;
            while((newLine = buffer.readLine()) != null){
                String[] columnFields = newLine.split(splitParameter, -1);
                propertySpecsDataClass importedList = new propertySpecsDataClass(columnFields[0], columnFields[1], columnFields[2], columnFields[3], columnFields[4], columnFields[5], columnFields[6], columnFields[7], columnFields[8], columnFields[9], columnFields[10], columnFields[11], columnFields[12]);
                propertySpecsList.add(importedList);
            }
        }
        catch(FileNotFoundException ex){
            logger.log(System.Logger.Level.ERROR,"File to be imported Not found" );


            System.out.println("File to be imported Not found");
        }
        catch(IOException ex){
            logger.log(System.Logger.Level.ERROR,"Error reading imported file" );


            System.out.println("Error reading imported file");
        }
    }
    //takes data from tableview and converts it to a csv file
    public static void exportData( ObservableList<propertySpecsDataClass> propertySpecsList) throws IOException {
        logger.log(System.Logger.Level.INFO,"Starting exportData method" );


        ObservableList<propertySpecsDataClass> exportList = FXCollections.observableArrayList();
        exportList.addAll(propertySpecsList);
        Writer writer = null;
        try {
            File file = new File("./ExportedProperty.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            for (propertySpecsDataClass exportCustomerData : exportList) {

                String text = exportCustomerData.getId() + "," + exportCustomerData.getListed() + "," + exportCustomerData.getBedroom() + "," + exportCustomerData.getBathroom()  + "," + exportCustomerData.getBedroom() + "," + exportCustomerData.getRent()  + "," + exportCustomerData.getSize() + "," + exportCustomerData.getPost() + "," + exportCustomerData.getLatitude() + "," + exportCustomerData.getLongitude()  + "," + exportCustomerData.getFurnish() + "," + exportCustomerData.getType() + "," + exportCustomerData.getGarden()  + "," + exportCustomerData.getAvailable()  + "\n";
                writer.write(text);

            }
        } catch (Exception ex) {
            logger.log(System.Logger.Level.ERROR,"Error writing to csv" );


            ex.printStackTrace();
        }
        finally {

            assert writer != null;
            writer.flush();
            writer.close();
        }
    }
    //deserializes the already serialized file
    public static void  saveToDataBase(ArrayList<String> customerDetailsArray, String path) throws IOException, ClassNotFoundException {
        logger.log(System.Logger.Level.INFO,"Starting saveToDataBase method" );


        try {
            FileOutputStream fos_lekan = new FileOutputStream(path);
            // write object to file
            ObjectOutputStream oos_lekan = new ObjectOutputStream(fos_lekan);
            oos_lekan.writeObject(customerDetailsArray);
            fos_lekan.close();
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR,"Error during serialization" );

            e.printStackTrace();
        }

        readCustomer();
    }


    //serializes arraylist into a file and also genarate csv to be displayed on the table view
    public static void readCustomer() throws IOException, ClassNotFoundException {
        logger.log(System.Logger.Level.INFO,"Starting readCustomer method" );

        List database;
        FileInputStream fis = new FileInputStream("./propertySpecsDatabase.lekan");
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
            File file = new File("./propertySpecsDataBase.csv.");
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
    private static final DecimalFormat decimalFormat= new DecimalFormat("0.00");

    public static double distance(double distance_latitude1, double distance_latitude2, double distance_longitude1, double distance_longitude){
        logger.log(System.Logger.Level.INFO,"Starting distance method" );


        distance_longitude1 = Math.toRadians(distance_longitude1);
        distance_longitude = Math.toRadians(distance_longitude);
        distance_latitude1 = Math.toRadians(distance_latitude1);
        distance_latitude2 = Math.toRadians(distance_latitude2);
        double dlon = distance_longitude - distance_longitude1;
        double dlat = distance_latitude2 - distance_latitude1;
        double a = Math.pow(Math.sin(dlat/2), 2)
                + Math.cos(distance_latitude1) * Math.cos(distance_latitude2)
                * Math.pow(Math.sin(dlon/2),2);
        double b = 2 * Math.asin(Math.sqrt(a));

        double c = 6371 * 0.621;
        double result = Double.parseDouble(decimalFormat.format( c*b));

        return result;
    }


}

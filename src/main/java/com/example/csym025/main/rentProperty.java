package com.example.csym025.main;

import com.example.csym025.customerDataClass;
import com.example.csym025.propertySpecsDataClass;
import com.example.csym025.rentDataClass;
import javafx.collections.ObservableList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class rentProperty {

    //Test log
    private static System.Logger logger= System.getLogger("rentPropertyClassLog");


    //generate 4 random digits
    public static int generateInvoiceNumber() {
        logger.log(System.Logger.Level.INFO,"Starting generateInvoiceNumber method" );

        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
    //gets system current date
    public static String getCurrentDate() {
        logger.log(System.Logger.Level.INFO,"Starting getCurrentData method" );

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    //serialize arraylist to the filepath
    public static void saveToDataRentBase(ArrayList<String> rentArray, String file) throws IOException, ClassNotFoundException {
        logger.log(System.Logger.Level.INFO,"Starting saveToDataRentBase method" );


        try {
            FileOutputStream fos_lekan = new FileOutputStream(file);
            // write object to file
            ObjectOutputStream oos_lekan = new ObjectOutputStream(fos_lekan);
            oos_lekan.writeObject(rentArray);
            fos_lekan.close();
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR,"Error serializing" );

            e.printStackTrace();
        }

        readCustomerRent(file);
    }

    private static void readCustomerRent(String serilizedFilePAth) throws IOException, ClassNotFoundException {
        logger.log(System.Logger.Level.INFO,"Starting readCustomerRent method" );

        List database;
        FileInputStream fis = new FileInputStream(serilizedFilePAth);
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
            File file = new File("./rentDataBase.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(databaseString);
        } catch (Exception ex) {
            logger.log(System.Logger.Level.ERROR,"Error writing csv" );

            ex.printStackTrace();
        } finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }
    //imports csv file onto tableview
    public static void importsavedCustomerData(String filePath, ObservableList<customerDataClass> custmerList) {
        logger.log(System.Logger.Level.INFO,"Starting importsavedCustomerData method" );

        String splitParameter = ",";
        BufferedReader buffer;

        try{
            buffer = new BufferedReader(new FileReader(filePath));
            String newLine;
            while((newLine = buffer.readLine()) != null){
                String[] columnFields = newLine.split(splitParameter, -1);
                customerDataClass importedList = new customerDataClass(columnFields[0], columnFields[1], columnFields[2], columnFields[3]);
                custmerList.add(importedList);
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
    //stores data from the file path in the propertySpecsList
    public static void importpropertySpecs(String s, ObservableList<propertySpecsDataClass> propertySpecsList){
        logger.log(System.Logger.Level.INFO,"Starting importpropertySpecs method" );


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
    //gets data from the string path and adds it to the rentList
    public static void importData(String s, ObservableList<rentDataClass> rentList) {
        logger.log(System.Logger.Level.INFO,"Starting importData method" );

        String splitParameter = ",";
        BufferedReader buffer;

        try{
            buffer = new BufferedReader(new FileReader(s));
            String newLine;
            while((newLine = buffer.readLine()) != null){
                String[] columnFields = newLine.split(splitParameter, -1);
                rentDataClass importedList = new rentDataClass(columnFields[0], columnFields[1], columnFields[2], columnFields[3], columnFields[4], columnFields[5], columnFields[6], columnFields[7], columnFields[8]);
                rentList.add(importedList);
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


    //generate invoice txt file
    public static void invoiceGenerator (String invoicePath,double depositFee,String invoiceNumber,String invoiceDate,String customerName,String customerPhone, String customerEmail,String rentedDate,String endOfTenency,String propertyDescription,double agentFee,double damagesFee,double total) throws IOException {
        logger.log(System.Logger.Level.INFO,"Starting invoiceGenerator method" );

        Writer writer = null;
        try {
            File file = new File(invoicePath);
            writer = new BufferedWriter(new FileWriter(file));
            String text = "Invoice Number :" +invoiceNumber +"\n"+
                    "Invoice Date :" + invoiceDate +"\n"+
                    "Bill to :"+"\n"+
                    "Customer Name :"+ customerName+"\n"+
                    "Customer Phone :"+customerPhone+"\n"+
                    "Customer Email :" + customerEmail+"\n"+
                    "Rented Data :" + rentedDate+"   End Of Tenancy :" + endOfTenency +"\n"+
                    "Property Description :" +propertyDescription +"\n"+
                    "Agent Fee :" + agentFee+"\n"+
                    "Deposit Fee :" + depositFee+"\n"+
                    "Damages :"+damagesFee+"\n"+
                    "Total :" + total;
            writer.write(text);

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


}

package org.vntu.cload;

//import java.io.File;
import java.io.FileReader;

import com.opencsv.CSVReader;

class Iqr {

  public void readData(String fileName) {
    try {
        // Create an object of filereader
        // class with CSV file as a parameter.
        FileReader fileReader = new FileReader(fileName);
 
        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;
 
        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
            for (String cell : nextRecord) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
        csvReader.close();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
}



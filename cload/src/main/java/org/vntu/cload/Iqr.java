package org.vntu.cload;

//import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.opencsv.CSVReader;

class Iqr {

  List<Double> dataIQR = new ArrayList<Double>();

  public void readData(String fileName) {
    try {
        // Create an object of filereader
        // class with CSV file as a parameter.
        FileReader fileReader = new FileReader(fileName);
 
        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;
//        List<Double> dataIQR = new ArrayList<Double>();

        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
        // For diagnostics purpose
            System.out.println(" Output: " + nextRecord[0] + " ; " + nextRecord[3]);
            dataIQR.add(Double.valueOf(nextRecord[3]));
        }
        csvReader.close();
        Collections.sort(dataIQR);
        System.out.println("Massive Outliers: " + getOutliers(dataIQR));
    }
    catch (Exception e) {
        e.printStackTrace();
    }
  }

  private List<Double> getOutliers(List<Double> inputDataIqr) {
    List<Double> outputDataIqr = new ArrayList<Double>();
    List<Double> data1 = new ArrayList<Double>();
    List<Double> data2 = new ArrayList<Double>();

    if (inputDataIqr.size() % 2 == 0) {
      data1 = inputDataIqr.subList(0, inputDataIqr.size() / 2);
      data2 = inputDataIqr.subList(inputDataIqr.size() / 2, inputDataIqr.size());
    } else {
      data1 = inputDataIqr.subList(0, inputDataIqr.size() / 2);
      data2 = inputDataIqr.subList(inputDataIqr.size() / 2 + 1, inputDataIqr.size());
    }

    double q1 = getMedian(data1);
    double q3 = getMedian(data2);
    double iqr = q3 - q1;
    double lowerFence = q1 - 1.5 * iqr;
    double upperFence = q3 + 1.5 * iqr;

    System.out.println("iqr:  " + iqr);
    System.out.println("min:  " + inputDataIqr.get(0));
    System.out.println("max:  " + inputDataIqr.get(inputDataIqr.size() - 1));
    System.out.println("lowerFence:  " + lowerFence);
    System.out.println("upperFence:  " + upperFence);
    for (int count = 0; count < inputDataIqr.size(); count++) {
      if (inputDataIqr.get(count) < lowerFence || inputDataIqr.get(count) > upperFence)
         outputDataIqr.add(inputDataIqr.get(count));
      }
    return outputDataIqr;
  }

  private double getMedian(List<Double> data) {
    if (data.size() % 2 == 0)
      return (data.get(data.size() / 2) + data.get(data.size() / 2 - 1)) / 2;
    else
      return data.get(data.size() / 2);
    }
}



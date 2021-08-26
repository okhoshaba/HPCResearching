package org.vntu.cload;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.opencsv.CSVReader;

class Iqr {

  List<Double> dataIQR = new ArrayList<Double>();
  List<Double> dataFirstLowerFence = new ArrayList<Double>();
  List<Double> dataSecondLowerFence = new ArrayList<Double>();
  List<Double> dataFirstUpperFence = new ArrayList<Double>();
  List<Double> dataSecondUpperFence = new ArrayList<Double>();

  public void processingData(DataForIqr dataForIqr) {
    try {
        // Create an object of filereader
        // class with CSV file as a parameter.
        FileReader fileReader = new FileReader(dataForIqr.getInputFile());
 
        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;

        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
        // For diagnostics purpose
            System.out.println(" Output: " + nextRecord[0] + " ; " + nextRecord[3]);
            dataIQR.add(Double.valueOf(nextRecord[3]));
        }
        csvReader.close();
        Collections.sort(dataIQR);
        defineOutliers(dataIQR);
        System.out.println("Massive Outliers for First Lower Fence: " + dataFirstLowerFence.toString());
        System.out.println("Massive Outliers for Second Lower Fence: " + dataSecondLowerFence.toString());
        System.out.println("Massive Outliers for First Upper Fence: " + dataFirstUpperFence.toString());
        System.out.println("Massive Outliers for Second Upper Fence: " + dataSecondUpperFence.toString());
    }
    catch (Exception e) {
        e.printStackTrace();
    }
  }

  private void defineOutliers(List<Double> inputDataIqr) {
    List<Double> dataFirstHalf = new ArrayList<Double>();
    List<Double> dataSecondHalf = new ArrayList<Double>();

    if (inputDataIqr.size() % 2 == 0) {
      dataFirstHalf = inputDataIqr.subList(0, inputDataIqr.size() / 2);
      dataSecondHalf = inputDataIqr.subList(inputDataIqr.size() / 2, inputDataIqr.size());
    } else {
      dataFirstHalf = inputDataIqr.subList(0, inputDataIqr.size() / 2);
      dataSecondHalf = inputDataIqr.subList(inputDataIqr.size() / 2 + 1, inputDataIqr.size());
    }

    double perc25 = getMedian(dataFirstHalf);
    double perc75 = getMedian(dataSecondHalf);
    double iqr = perc75 - perc25;
    double perc50 = (perc75 + perc25) / 2;
    double firstLowerFence = perc25 - 1.5 * iqr;
    double firstUpperFence = perc75 + 1.5 * iqr;
    double secondLowerFence = perc25 - 3 * iqr;
    double secondUpperFence = perc75 + 3 * iqr;

    System.out.println("First Quartile (25th percentiles):  " + perc25);
    System.out.println("Second Quartile (25th percentiles):  " + perc50);
    System.out.println("Third Quartile (75th percentiles):  " + perc75);
    System.out.println("Interquartile Range (IQR):  " + iqr);
    System.out.println("Min:  " + inputDataIqr.get(0));
    System.out.println("Max:  " + inputDataIqr.get(inputDataIqr.size() - 1));
    System.out.println("Second Lower Fence:  " + secondLowerFence);
    System.out.println("First Lower Fence:  " + firstLowerFence);
    System.out.println("First Upper Fence:  " + firstUpperFence);
    System.out.println("Second Upper Fence:  " + secondUpperFence);
    for (int count = 0; count < inputDataIqr.size(); count++) {
      if (inputDataIqr.get(count) < secondLowerFence)
        dataSecondLowerFence.add(inputDataIqr.get(count));
      else
        if (inputDataIqr.get(count) < firstLowerFence)
          dataFirstLowerFence.add(inputDataIqr.get(count));
      else
      if (inputDataIqr.get(count) > secondUpperFence)
        dataSecondUpperFence.add(inputDataIqr.get(count));
      else
        if (inputDataIqr.get(count) > secondUpperFence)
          dataFirstUpperFence.add(inputDataIqr.get(count));
//      if (inputDataIqr.get(count) < firstLowerFence || inputDataIqr.get(count) > firstUpperFence)
//        dataFirstLowerFence.add(inputDataIqr.get(count));
//        outputDataIqr.add(inputDataIqr.get(count));
      }
//    return outputDataIqr;
  }

  private double getMedian(List<Double> data) {
    if (data.size() % 2 == 0)
      return (data.get(data.size() / 2) + data.get(data.size() / 2 - 1)) / 2;
    else
      return data.get(data.size() / 2);
    }
}



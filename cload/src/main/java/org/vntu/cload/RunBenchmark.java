package org.vntu.cload;

import java.io.File;
import java.io.FileWriter;

import com.opencsv.CSVWriter;

class RunBenchmark implements Runnable {
  static int globalNumber = 0;    
  String address;
  String port;
  String fileName;
  int requests;

  RunBenchmark(DataForRunBenchmark dataForRunBenchmark) {
    this.address = dataForRunBenchmark.getAddress();
    this.port = dataForRunBenchmark.getPort();
    this.requests = Integer.parseInt(dataForRunBenchmark.getRequests());
    this.fileName = dataForRunBenchmark.getFileName();
  } 

  public void run() {
    try   {
      long start, stop, responceTime;

// For save to Disk:
      File file = new File(this.fileName);
//File file = new File("data.txt");
      FileWriter outputfile = new FileWriter(file, true);
  
// create CSVWriter object filewriter object as parameter
      CSVWriter writer = new CSVWriter(outputfile);
  

      for (int count = 0; count < this.requests; count++) {
        start = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("curl " + this.address + ":" + this.port + " > /dev/null 2>&1");
        process.waitFor();
        stop = System.currentTimeMillis();
        responceTime = stop - start;
        globalNumber++;

// For diagnostic purpose
//      System.out.println("number = " + Number.globalNumber++ + " start = " + start + " stop = " + stop + " ; total = ; " + responceTime);

// add data to csv
        String[] data = { String.valueOf(globalNumber), String.valueOf(start), String.valueOf(stop), String.valueOf(responceTime) };
      writer.writeNext(data);
      }
// closing writer connection
        writer.close();
      }
      catch (Exception e)   {     // Throwing an exception
        System.out.println ("Exception is caught");
      }
  }
}



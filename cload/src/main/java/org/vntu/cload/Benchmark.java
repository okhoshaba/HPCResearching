package org.vntu.cload;

import java.io.File;
import java.io.FileWriter;

import com.opencsv.CSVWriter;

public class Benchmark {

  private String address;
  private String port;
  private String series;
  private String requests;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getRequests() {
    return requests;
  }

  public void setRequests(String requests) {
    this.requests = requests;
  }

//  public void buildBenchmark() throws InterruptedException {
  public void buildBenchmark() {
    int limit_series = Integer.parseInt(this.series);
    Long periodOfTime = 1000/(Long.parseLong(this.series) * Long.parseLong(this.requests) );

    System.out.println("For diagnostic purpose only: var periodOfTime = " + periodOfTime);

    for (int icount = 0; icount < 10; icount++) 
    for (int count = 0; count < limit_series; count++) {
      Thread object = new Thread(new RunBenchmark(this.address, this.port, this.requests));
      object.start();
      try {
        Thread.sleep(periodOfTime);
       } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new AssertionError(e);
       }
     }
   }
}

class RunBenchmark implements Runnable {
  String address;
  String port;
  int requests;

  RunBenchmark(String address, String port, String requests) {
    this.address = address;
    this.port = port;
    this.requests = Integer.parseInt(requests);
  } 

  public void run() {
    try   {
      long start, stop, responceTime;

// For save to Disk:
File file = new File("data.txt");
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
      Numbers.globalNumber++;

// For diagnostic purpose
//      System.out.println("number = " + Number.globalNumber++ + " start = " + start + " stop = " + stop + " ; total = ; " + (stop - start));

// adding header to csv
//      String[] header = { "Number", "Start", "Stop", "Total" };
//      writer.writeNext(header);

  
// add data to csv
      String[] data = { String.valueOf(Numbers.globalNumber), String.valueOf(start), String.valueOf(stop), String.valueOf(responceTime) };

writer.writeNext(data);
  
    }
// closing writer connection
writer.close();
// }

//catch (IOException e) {
//e.printStackTrace();
//}

    }
    catch (Exception e)   {     // Throwing an exception
      System.out.println ("Exception is caught");
    }
  }
}

class Numbers {    
  static int globalNumber = 0;    
} 



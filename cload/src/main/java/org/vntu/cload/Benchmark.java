package org.vntu.cload;

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

    for (int count = 0; count < limit_series; count++) {
      Thread object = new Thread(new RunBenchmark(this.address, this.port));
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

  RunBenchmark(String address, String port) {
    this.address = address;
    this.port = port;
  } 
  public void run() {
    try   {
      long start = System.currentTimeMillis();

      Runtime runtime = Runtime.getRuntime();
//    System.out.println("For diagnostic purpose only: var address = " + this.address + " , port: " + this.port);
      Process process = runtime.exec("curl " + this.address + ":" + this.port + " > /dev/null 2>&1");
      process.waitFor();
      System.out.println("u1 ; " + Number.globalNumber++ + " ; y ; " + (System.currentTimeMillis() - start));

// For save to Disk:
    }
    catch (Exception e)   {     // Throwing an exception
      System.out.println ("Exception is caught");
    }
  }
}

class Number {    
  static int globalNumber = 1;    
} 



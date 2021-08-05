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

//    Long periodOfTime = 1000/(Long.parseLong(series) * Long.parseLong(requests) );

    for (int count = 0; count < 30; count++) {
      Thread object = new Thread(new RunBenchmark());
      object.start();
//      Thread.sleep(periodOfTime);
      try {
        Thread.sleep(50);
       } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new AssertionError(e);
        }
      }
    }
}

class RunBenchmark implements Runnable {
public void run() {
try   {     // Displaying the thread that is running
long start = System.currentTimeMillis();

//String name1, name2, name3;
Runtime runtime = Runtime.getRuntime();
//Process process = runtime.exec(name1 + name2 + name3);
  Process process = runtime.exec("curl " + "localhost:8081" + " > /dev/null 2>&1");
//  Process process = runtime.exec("curl localhost:8081 > /dev/null 2>&1");
// For diagnostic only
process.waitFor();
//System.out.println("u1 ; 1 ; u2 ; " + Number.globalNumber++ + "; y ; " + (System.currentTimeMillis() - start));
System.out.println("u1 ; 1 ; u2 ; 1 ; y ; " + (System.currentTimeMillis() - start));
}
catch (Exception e)   {     // Throwing an exception
System.out.println ("Exception is caught");
}
}
}




package org.vntu.cload;

public class InputData {

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

    for (int icount = 0; icount < 1; icount++) 
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


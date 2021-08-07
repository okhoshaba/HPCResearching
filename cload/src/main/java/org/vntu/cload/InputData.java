package org.vntu.cload;

public class InputData {

  private String address;
  private String port;
  private String series;
  private String requests;
  private String durationTime;
  private String fileName;

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
  public String getDurationTime() {
    return durationTime;
  }

  public void setDurationTime(String durationTime) {
    this.durationTime = durationTime;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void buildBenchmark() {
    int limit_series = Integer.parseInt(this.series);
    int limit_duration = Integer.parseInt(this.durationTime);
    Long periodOfTime = 1000/(Long.parseLong(this.series) * Long.parseLong(this.requests) );

    System.out.println("For diagnostic purpose only: var periodOfTime = " + periodOfTime);

    for (int durationCount = 0; durationCount < limit_duration; durationCount++) 
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


package org.vntu.cload;

public class InputData {

  private String series;
  private String durationTime;

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getDurationTime() {
    return durationTime;
  }

  public void setDurationTime(String durationTime) {
    this.durationTime = durationTime;
  }

  public void buildBenchmark(DataForRunBenchmark dataForRunBenchmark) {
    int limit_series = Integer.parseInt(this.series);
    int limit_duration = Integer.parseInt(this.durationTime);
    Long periodOfTime = 1000/(Long.parseLong(this.series) * Long.parseLong(dataForRunBenchmark.getRequests()) );

    System.out.println("For diagnostic purpose only: var periodOfTime = " + periodOfTime);

    for (int durationCount = 0; durationCount < limit_duration; durationCount++) 
      for (int count = 0; count < limit_series; count++) {
//        Thread object = new Thread(new RunBenchmark(this.address, this.port, this.requests, this.fileName));
        Thread object = new Thread(new RunBenchmark(dataForRunBenchmark));
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


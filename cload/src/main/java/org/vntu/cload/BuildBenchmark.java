package org.vntu.cload;

public class BuildBenchmark {

  public void buildBenchmark(DataForRunBenchmark dataForRunBenchmark, DataForBuildBenchmark dataForBuildBenchmark) {
    int limit_series = Integer.parseInt(dataForBuildBenchmark.getSeries());
    int limit_duration = Integer.parseInt(dataForBuildBenchmark.getDurationTime());
    Long periodOfTime = 1000/(Long.parseLong(dataForBuildBenchmark.getSeries()) * Long.parseLong(dataForRunBenchmark.getRequests()) );

    System.out.println("For diagnostic purpose only: var periodOfTime = " + periodOfTime);

    for (int durationCount = 0; durationCount < limit_duration; durationCount++) 
      for (int count = 0; count < limit_series; count++) {
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


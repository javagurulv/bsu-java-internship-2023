package lv.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;

public class LoadTestingSystem {

    public static void main(String[] args) {
        new LoadTestingSystem().executeForAMinute(1, 60000);
    }
    private void executeForAMinute(int parallelThreadCount, int requestCount) {
        long intervalBetweenRequestsInMillis = 60000L / requestCount;
        LoadTestingStatistic loadTestingV1Statistic = new LoadTestingStatistic();
        List<Thread> threads = new ArrayList<>();
        int commonRequestCount = requestCount * parallelThreadCount;
        for (int i = 0; i < commonRequestCount; i++) {
            threads.add(new Thread(new V1Call(loadTestingV1Statistic)));
        }
        for (int i = 0; i < requestCount; i++) {
            for (int j = 0; j < parallelThreadCount; j++) {
                Thread thread = new Thread(new V1Call(loadTestingV1Statistic));
                thread.start();
                threads.add(thread);
            }
            try {
                Thread.sleep(intervalBetweenRequestsInMillis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Average request processing time for 1st version (ms): " + loadTestingV1Statistic.getAverageExecutionTime());
        System.out.println("Minimum request processing time for 1st version (ms): " + loadTestingV1Statistic.getMinExecutionTime());
        System.out.println("Maximum request processing time for 1st version (ms): " + loadTestingV1Statistic.getMaxExecutionTime());
    }
}

package lv.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;

public class LoadTestingSystem {
    public static void main(String[] args) {
        new LoadTestingSystem().executeForAMinute(5, 500L);
    }

    public void executeForAMinute(long parallelThreadsCount, long requestsCount) {
        long timeBetweenTwoRequests = 60000L / requestsCount; //in milliseconds
        List<Thread> threads = new ArrayList<>();
        LoadTestingStatistic statisticV1 = new LoadTestingStatistic();

        for (long i = 1; i < parallelThreadsCount; i++) {
            for (long j = 1; j < requestsCount; j++) {
                Thread thread = new Thread(new V1Call(statisticV1));
                threads.add(thread);
                thread.start();

                try {
                    Thread.sleep(timeBetweenTwoRequests);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Maximal time V1 : " + statisticV1.max() + " ms");
        System.out.println("Minimal time V1 : " + statisticV1.min() + " ms");
        System.out.println("Average time V1 : " + statisticV1.avg() + " ms");
    }
}

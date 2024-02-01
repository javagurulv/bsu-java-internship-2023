package lv.javaguru.travel.insurance.core.loadtesting;

import java.util.ArrayList;
import java.util.List;

class LoadTestingSystem {

    public static void main(String[] args) {
        new LoadTestingSystem().executeForAMinute(5, 250);
    }

    public void executeForAMinute(int parallelThreadCount, int requestCount) {
        long intervalBetweenRequestsInMillis = 60000L / requestCount;

        LoadTestingStatistic statisticV1 = new LoadTestingStatistic();

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= requestCount; i++) {
            for (int j = 1; j <= parallelThreadCount; j++) {
                Thread v1Call = new Thread(new V1Call(statisticV1));
                v1Call.start();
                threads.add(v1Call);
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

        System.out.println("V1 average = " + statisticV1.avg());
        System.out.println("V1 min = " + statisticV1.min());
        System.out.println("V1 max = " + statisticV1.max());
    }

}

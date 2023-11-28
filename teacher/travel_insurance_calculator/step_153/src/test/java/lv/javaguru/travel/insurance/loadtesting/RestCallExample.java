package lv.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;

class RestCallExample {

    public static void main(String[] args) {
        LoadTestingStatistic statisticV1 = new LoadTestingStatistic();
        LoadTestingStatistic statisticV2 = new LoadTestingStatistic();

        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Thread v1Call = new Thread(new V1Call(statisticV1));
            Thread v2Call = new Thread(new V2Call(statisticV2));
            v1Call.start();
            v2Call.start();
            threads.add(v1Call);
            threads.add(v2Call);
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("V1 average = " + statisticV1.calculateAverage());
        System.out.println("V1 min = " + statisticV1.findMin());
        System.out.println("V1 max = " + statisticV1.findMax());

        System.out.println("V2 average = " + statisticV2.calculateAverage());
        System.out.println("V2 min = " + statisticV2.findMin());
        System.out.println("V2 max = " + statisticV2.findMax());
    }

}

package lv.javaguru.travel.insurance.core.loadtesting;

import java.util.ArrayList;
import java.util.List;

class RestCallExample {

    public static void main(String[] args) {
        LoadTestingStatistic statisticV1 = new LoadTestingStatistic();
        LoadTestingStatistic statisticV2 = new LoadTestingStatistic();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Thread threadV1 = new Thread(new V1Call(statisticV1));
            threads.add(threadV1);
            threadV1.start();

            Thread threadV2 = new Thread(new V2Call(statisticV2));
            threads.add(threadV2);
            threadV2.start();
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Max v1 request processing time: " + statisticV1.max());
        System.out.println("Min v1 request processing time: " + statisticV1.min());
        System.out.println("Average v1 request processing time: " + statisticV1.avg());
        System.out.println("Max v2 request processing time: " + statisticV2.max());
        System.out.println("Min v2 request processing time: " + statisticV2.min());
        System.out.println("Average v2 request processing time: " + statisticV2.avg());
    }

}
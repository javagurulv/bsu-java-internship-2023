package lv.javaguru.travel.insurance.loadtesting;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestCallExample {
    public static void main(String[] args) {
        LoadTestingStatistic loadTestingV1Statistic = new LoadTestingStatistic();
        LoadTestingStatistic loadTestingV2Statistic = new LoadTestingStatistic();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            threads.add(new Thread(new V1Call(loadTestingV1Statistic)));
            threads.add(new Thread(new V2Call(loadTestingV2Statistic)));
        }
        threads.forEach(Thread::start);
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
        System.out.println("Average request processing time for 2nd version (ms): " + loadTestingV2Statistic.getAverageExecutionTime());
        System.out.println("Minimum request processing time for 2nd version (ms): " + loadTestingV2Statistic.getMinExecutionTime());
        System.out.println("Maximum request processing time for 2nd version (ms): " + loadTestingV2Statistic.getMaxExecutionTime());

    }
}

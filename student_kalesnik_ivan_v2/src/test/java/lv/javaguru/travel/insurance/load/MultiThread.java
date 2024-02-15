package lv.javaguru.travel.insurance.load;

import java.util.ArrayList;
import java.util.List;

public class MultiThread {

    public static void main(String[] args) {
        new MultiThread().execute(1, 21);
    }
    private void execute(int parallelThreadCount, int requestCount) {
        long intervalBetweenRequestsInMillis = 60000L / requestCount;
        SynchroLoad loadTestingV1Statistic = new SynchroLoad();
        List<Thread> threads = new ArrayList<>();
        int commonRequestCount = requestCount * parallelThreadCount;
        for (int i = 0; i < commonRequestCount; i++) {
            threads.add(new Thread(new Call(loadTestingV1Statistic)));
        }
        for (int i = 0; i < requestCount; i++) {
            for (int j = 0; j < parallelThreadCount; j++) {
                Thread thread = new Thread(new Call(loadTestingV1Statistic));
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

        System.out.println("Average (ms): " + loadTestingV1Statistic.getAverageExecutionTime());
        System.out.println("Min (ms): " + loadTestingV1Statistic.getMinExecutionTime());
        System.out.println("Max (ms): " + loadTestingV1Statistic.getMaxExecutionTime());
    }
}

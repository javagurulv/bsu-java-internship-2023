package lv.javaguru.travel.insurance.loadtesting;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadTestingStatistic {
    private List<Long> executionTimes = new ArrayList<>();

    synchronized void add(Long time) {
        executionTimes.add(time);
    }

    synchronized Double getAverageExecutionTime() {
        return executionTimes.stream().mapToLong(time->time).average().getAsDouble();
    }

    synchronized Long getMinExecutionTime() {
        return executionTimes.stream().mapToLong(time->time).min().getAsLong();
    }

    synchronized Long getMaxExecutionTime() {
        return executionTimes.stream().mapToLong(time->time).max().getAsLong();
    }
}

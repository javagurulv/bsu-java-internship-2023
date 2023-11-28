package lv.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

class LoadTestingStatistic {

    private List<Long> executionTimes = new ArrayList<>();

    public synchronized void addExecutionTime(Long executionTime) {
        executionTimes.add(executionTime);
    }


    public synchronized Double calculateAverage() {
        OptionalDouble average = executionTimes.stream()
                .mapToDouble(a -> a)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public synchronized Long findMax() {
        return executionTimes.stream()
                .max(Long::compareTo)
                .orElse(0L);
    }

    public synchronized Long findMin() {
        return executionTimes.stream()
                .min(Long::compareTo)
                .orElse(0L);
    }

}
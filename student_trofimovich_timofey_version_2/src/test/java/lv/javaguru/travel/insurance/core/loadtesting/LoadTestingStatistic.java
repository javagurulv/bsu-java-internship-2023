package lv.javaguru.travel.insurance.core.loadtesting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

public class LoadTestingStatistic {
    private List<Long> executionTimes = new ArrayList<>();

    synchronized void add(Long value) {
        executionTimes.add(value);
    }

    synchronized Long max() {
        return executionTimes.stream().
                max(Comparator.naturalOrder())
                .orElse(0L);
    }

    synchronized Long min() {
        return executionTimes.stream().
                min(Comparator.naturalOrder())
                .orElse(0L);
    }


    synchronized Double avg() {
        OptionalDouble average = executionTimes.stream()
                .mapToDouble(v -> v)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}

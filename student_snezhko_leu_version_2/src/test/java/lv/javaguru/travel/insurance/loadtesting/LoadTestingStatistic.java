package lv.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoadTestingStatistic {
    private List<Long> executionTime = new ArrayList<>();

    public synchronized void add(Long time) {
        executionTime.add(time);
    }

    public synchronized Long max() {
        return executionTime.stream().max(Comparator.naturalOrder()).orElse(null);
    }

    public synchronized Long min() {
        return executionTime.stream().min(Comparator.naturalOrder()).orElse(null);
    }

    public synchronized Double avg() {
        return executionTime.stream()
                .mapToDouble(Long::doubleValue)
                .average()
                .orElse(0)
                ;
    }
}

package lv.javaguru.travel.insurance.core.loadtesting;

import lombok.SneakyThrows;

public class V1Call implements Runnable {
    private LoadTestingStatistic statistic;
    @SneakyThrows
    @Override
    public void run() {
        CommonCall.executeCall("http://localhost:8080/insurance/travel/api/v1/",
                "rest/v1/agreement/no error, all fields provided/request.json",
                "rest/v1/agreement/no error, all fields provided/response.json",
                statistic);
    }

    public V1Call(LoadTestingStatistic statistic) {
        this.statistic = statistic;
    }
}

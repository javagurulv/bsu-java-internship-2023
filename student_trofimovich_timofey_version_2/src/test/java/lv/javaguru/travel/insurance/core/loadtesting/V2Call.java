package lv.javaguru.travel.insurance.core.loadtesting;

import lombok.SneakyThrows;

public class V2Call implements Runnable {
    private LoadTestingStatistic statistic;
    @SneakyThrows
    @Override
    public void run() {
        CommonCall.executeCall("http://localhost:8080/insurance/travel/api/v2/",
                "rest/v2/agreement/no error, all fields provided/request.json",
                "rest/v2/agreement/no error, all fields provided/response.json",
                statistic);
    }

    public V2Call(LoadTestingStatistic statistic) {
        this.statistic = statistic;
    }
}

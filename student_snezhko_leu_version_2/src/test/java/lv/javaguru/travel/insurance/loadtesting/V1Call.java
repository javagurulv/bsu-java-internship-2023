package lv.javaguru.travel.insurance.loadtesting;

import org.json.JSONException;

import java.io.IOException;

public class V1Call extends CommonCall implements Runnable{

    private LoadTestingStatistic loadTestingStatistic;
    public V1Call(LoadTestingStatistic statistics) {
        loadTestingStatistic = statistics;
    }

    @Override
    public void run() {
        try {
            executeAndCompare("http://localhost:8080/insurance/travel/api/v1/",
                "rest/v1/correct_request/all fields filled/request.json",
                "rest/v1/correct_request/all fields filled/response.json",
                    loadTestingStatistic);
            //System.out.println("V1 time : " + time + " ms");
            //loadTestingStatistic.add(time);
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

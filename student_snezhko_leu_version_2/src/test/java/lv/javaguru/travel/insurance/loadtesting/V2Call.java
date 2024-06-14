package lv.javaguru.travel.insurance.loadtesting;

import org.json.JSONException;

import java.io.IOException;

public class V2Call extends CommonCall implements Runnable{
    private LoadTestingStatistic loadTestingStatistic;

    public V2Call(LoadTestingStatistic statistic) {
        loadTestingStatistic = statistic;
    }

    @Override
    public void run() {
        try {
            executeAndCompare("http://localhost:8080/insurance/travel/api/v2/",
                    "rest/v2/correct_request/correct request TM/request.json",
                    "rest/v2/correct_request/correct request TM/response.json",
                    loadTestingStatistic);
            //System.out.println("V2 time : " + time + " ms");
            //loadTestingStatistic.add(time);
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

package lv.javaguru.travel.insurance.loadtesting;

import org.json.JSONException;

import java.io.IOException;

public class V1Call extends CommonCall implements Runnable{
    @Override
    public void run() {
        try {
            long time = executeAndCompare("http://localhost:8080/insurance/travel/api/v1/",
                "rest/v1/correct_request/all fields filled/request.json",
                "rest/v1/correct_request/all fields filled/response.json");
            System.out.println("V1 time : " + time + " ms");
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.travel.insurance.rest.RemoveRandomValues.removeRandomValues;


public class RestCallExample {
    public static void main(String[] args) {
       List<Thread> threads = new ArrayList<>();
       LoadTestingStatistic statisticV1 = new LoadTestingStatistic();
       LoadTestingStatistic statisticV2 = new LoadTestingStatistic();

       for (int i = 0; i < 50; i++) {
           threads.add(new Thread(new V1Call(statisticV1)));
           threads.add(new Thread(new V2Call(statisticV2)));
       }

       threads.forEach(Thread::start);
       threads.forEach(thread -> {
           try {
               thread.join();
           }
           catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       });

        System.out.println("Maximal time V1 : " + statisticV1.max() + " ms");
        System.out.println("Minimal time V1 : " + statisticV1.min() + " ms");
        System.out.println("Average time V1 : " + statisticV1.avg() + " ms\n");

        System.out.println("Maximal time V2 : " + statisticV2.max() + " ms");
        System.out.println("Minimal time V2 : " + statisticV2.min() + " ms");
        System.out.println("Average time V2 : " + statisticV2.avg() + " ms");
    }
}
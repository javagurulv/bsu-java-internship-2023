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

import static lv.javaguru.travel.insurance.rest.RemoveRandomValues.removeRandomValues;


public class RestCallExample {
    public static void main(String[] args) {
       // try {

            for (int i = 0; i < 50; i++) {
                Thread v1 = new Thread(new V1Call());
                Thread v2 = new Thread(new V2Call());

                Stopwatch stopwatch = Stopwatch.createStarted();
                v1.start();
                v2.start();
            }
/*            v1.join();
            v2.join();

            stopwatch.stop();
            System.out.println("General time : " + stopwatch.elapsed().toMillis() + " ms");


        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
 */
    }
}
package lv.javaguru.travel.insurance.loadtesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Files;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;
import com.google.common.base.Stopwatch;

@Component
public class CommonCall {
    private RestTemplate restTemplate = new RestTemplate();
    public void sendRequest(String testCase, String url) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestFile = testCase + "/request.json";
        String responseFile = testCase + "/response.json";

        Stopwatch stopwatch = Stopwatch.createStarted();
        String response = restTemplate.postForObject(
                url,
                new HttpEntity<>(parseJSONIntoString(requestFile), headers),
                String.class);
        stopwatch.stop();
        System.out.println("Request processing time (ms): " + stopwatch.elapsed().toMillis());

        compareJsons(response, responseFile);
    }

    private void compareJsons(String response, String responseFile) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        assertJson(mapper.readTree(response))
                .where()
                .path("uuid")
                .matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(parseJSONIntoString(responseFile));
    }

    private String parseJSONIntoString(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

}

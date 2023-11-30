package lv.javaguru.travel.insurance.loadtesting;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

class CommonCall {

    protected void executeRestCallAndCompareResults(String jsonRequest,
                                                    String jsonExpectedResponse,
                                                    String url) {
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<String>(jsonRequest, headers);

        var responseBodyContent = restTemplate.postForObject(url, request, String.class);

        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .at("/uuid").isNotEmpty()
                .isEqualTo(jsonExpectedResponse);
    }

}

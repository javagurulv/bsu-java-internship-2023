package lv.javaguru.travel.insurance.rest.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class TravelGetPolicyControllerTestCase1 extends TravelGetPolicyControllerTest {
    private static final String URL_CALCULATE = "/insurance/travel/api/v2/";
    private final String requestFileName = "rest/internal/test_case_1/calculate_premium_request.json";


    @Override
    protected String getTestCaseName() {
        return "test_case_1";
    }

    @Override
    protected boolean testWithCorrectUuid() {
        return true;
    }

    @Override
    protected String providedUuid() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String calculateResponse = calculatePremium();
        return mapper.readTree(calculateResponse).get("uuid").asText();
    }

    private String calculatePremium() throws Exception {
        String calculateResponse = mockMvc.perform(post(URL_CALCULATE)
                        .content(parseJSONIntoString(requestFileName))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return calculateResponse;
    }

}

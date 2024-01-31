package lv.javaguru.travel.insurance.rest.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class TravelCalculatePremiumControllerTestCase6 extends TravelCalculatePremiumControllerTest {
    @Test
    public void TravelCalculatePremiumControllerTestCase6() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/test_case_6/PremiumWithoutRisksRequest.json";
        String pathResponse = "rest/test_case_6/PremiumWithoutRisksResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
}

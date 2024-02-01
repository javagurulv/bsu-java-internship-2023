package lv.javaguru.travel.insurance.rest.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class TravelCalculatePremiumControllerTestCase1 extends TravelCalculatePremiumControllerTest {
    @Test
    public void TravelCalculatePremiumControllerTestCase1() throws Exception {//throws FileNotFoundException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/test_case_1/TravelCalculatePremiumRequest.json";
        String pathResponse = "rest/test_case_1/TravelCalculatePremiumResponse.json";

        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //when(underwriting.calculatePremium(TravelCalculatePremiumResponse.class)).thenReturn()
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
}

package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorrectRequest() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_correct.json",
                "rest/TravelCalculatePremiumResponse_correct.json"
        );
    }

    @Test
    public void testTotallyWrongRequest() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_allWrong.json",
                "rest/TravelCalculatePremiumResponse_allWrong.json"
        );
    }

    @Test
    public void testWrongRequestDateFromIsEmpty() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_dateFromEmpty.json",
                "rest/TravelCalculatePremiumResponse_dateFromEmpty.json"
        );
    }

    @Test
    public void testWrongRequestDateToIsEmpty() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_dateToEmpty.json",
                "rest/TravelCalculatePremiumResponse_dateToEmpty.json"
        );
    }

    @Test
    public void testWrongRequestFirstNameIsEmpty() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_firstNameEmpty.json",
                "rest/TravelCalculatePremiumResponse_firstNameEmpty.json"
        );
    }

    @Test
    public void testWrongRequestLastNameIsEmpty() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_lastNameEmpty.json",
                "rest/TravelCalculatePremiumResponse_lastNameEmpty.json"
        );
    }

    @Test
    public void testWrongRequestDateSequenceIsWrong() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_dateSeq.json",
                "rest/TravelCalculatePremiumResponse_dateSeq.json"
        );
    }

    @Test
    public void testWrongRequestRisksNotSelected() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_risksNotSelected.json",
                "rest/TravelCalculatePremiumResponse_risksNotSelected.json"
        );
    }

    @Test
    public void testWrongRequestRisksNotSupported() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_unsupportedRisks.json",
                "rest/TravelCalculatePremiumResponse_unsupportedRisks.json"
        );
    }

    @Test
    public void testWrongRequestCountryIsNullWhenTravelMedicalRiskIsPresent() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_countryNull.json",
                "rest/TravelCalculatePremiumResponse_countryNull.json"
        );
    }

    @Test
    public void testWrongRequestCountryIsNotRegistered() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_countryUnknown.json",
                "rest/TravelCalculatePremiumResponse_countryUnknown.json"
        );
    }

    @Test
    public void testWrongRequestBirthDateIsNull() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_birthDateNull.json",
                "rest/TravelCalculatePremiumResponse_birthDateNull.json"
        );
    }

    @Test
    public void testWrongRequestBirthDateIsNotPast() throws Exception {
        compareResponseToRequestInJsonFiles(
                "rest/TravelCalculatePremiumRequest_birthDateNotPast.json",
                "rest/TravelCalculatePremiumResponse_birthDateNotPast.json"
        );
    }

    public void compareResponseToRequestInJsonFiles(
            String fileNameRequest, String fileNameResponse
    ) throws Exception {
        String jsonRequest = JsonReader.read(fileNameRequest);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = JsonReader.read(fileNameResponse);

        System.out.println(responseBodyContent);
        System.out.println(jsonResponse);

        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }
}

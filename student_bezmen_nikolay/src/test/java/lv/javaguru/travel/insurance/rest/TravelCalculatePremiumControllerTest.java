package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private readDataFromJsonFile readJson;
    @Autowired private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTest() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest.json",
                "rest/TravelCalculatePremiumResponse.json"
        );
    }

    @Test
    public void testCorrectResponseIfDateFromIsNull() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_dateFromIsNull.json",
                "rest/TravelCalculatePremiumResponse_dateFromIsNull.json"
        );
    }
    @Test
    public void testCorrectResponseIfDateToIsNull() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_dateToIsNull.json",
                "rest/TravelCalculatePremiumResponse_dateToIsNull.json"
        );
    }
    @Test
    public void testCorrectResponseIfDateToBeforeDateFrom() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_dateToBeforeDateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateToBeforeDateFrom.json"
        );
    }
    @Test
    public void testCorrectResponseIfDateToIsNullAndFirstNameIsEmptyAndLastNameIsNull() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_dateToIsNull_firstNameIsEmpty_lastNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_" +
                        "dateToIsNull_firstNameIsEmpty_lastNameIsNull.json"
        );
    }
    @Test
    public void testCorrectResponseIfFirstNameIsEmpty() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_firstNameIsEmpty.json",
                "rest/TravelCalculatePremiumResponse_firstNameIsEmpty.json"
        );
    }
    @Test
    public void testCorrectResponseIfFirstNameIsNull() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_firstNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_firstNameIsNull.json"
        );
    }
    @Test
    public void testCorrectResponseIfLastNameIsEmpty() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_lastNameIsEmpty.json",
                "rest/TravelCalculatePremiumResponse_lastNameIsEmpty.json"
        );
    }
    @Test
    public void testCorrectResponseIfLastNameIsNull() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_lastNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_lastNameIsNull.json"
        );
    }

    @Test
    public void testCorrectResponseIfDateToInPast() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest_dateToInPast.json",
                "rest/TravelCalculatePremiumResponse_dateToInPast.json"
        );
    }
    private void compareResponseFromRequest(String pathToRequestJsonFile,
                                                   String pathToCorrectResponseJsonFile) throws Exception {
        String jsonRequestToString = readJson.parseData(pathToRequestJsonFile);

        MvcResult resultOfResponse = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequestToString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseToString = resultOfResponse.getResponse().getContentAsString();

        String correctResponse = readJson.parseData(pathToCorrectResponseJsonFile);

        JSONAssert.assertEquals(correctResponse,responseToString, false);
    }

}
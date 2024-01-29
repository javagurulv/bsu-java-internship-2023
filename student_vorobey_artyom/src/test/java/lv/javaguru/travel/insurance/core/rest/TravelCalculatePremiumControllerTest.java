package lv.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpHeaders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void simpleRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json");
    }

    @Test
    public void shouldReturnErrorIfFirstNameIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_firstName_empty.json",
                "rest/TravelCalculatePremiumResponse_firstName_empty.json");
    }

    @Test
    public void shouldReturnErrorIfLastNameIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_lastName_empty.json",
                "rest/TravelCalculatePremiumResponse_lastName_empty.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_null.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_null.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateToIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateTo_null.json",
                "rest/TravelCalculatePremiumResponse_dateTo_null.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromAfterAgreementDateToRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_after_dateTo.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_after_dateTo.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromEqualsAgreementDateToRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_equals_dateTo.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_equals_dateTo.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsInPastRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_inPast.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_inPast.json");
    }

    @Test
    public void shouldReturnErrorIfSelectedRisksIsInvalidControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_selectedRisks_empty.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_empty.json");
    }

    private void executeAndCompare(String JsonFileRequestPath, String JsonFileResponsePath) throws Exception {
        String jsonStringRequest = jsonFileReader.readJsonFromFile(JsonFileRequestPath);
        String jsonExpectedStringResponse = jsonFileReader.readJsonFromFile(JsonFileResponsePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                .content(jsonStringRequest)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String jsonCurrentStringResponse = result.getResponse().getContentAsString();

        assertEquals(mapper.readTree(jsonExpectedStringResponse), mapper.readTree(jsonCurrentStringResponse));
    }
}

package lv.javaguru.travel.insurance.rest;

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
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void successRequest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json");
    }

    @Test
    public void firstNameIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_firstName_empty.json",
                "rest/TravelCalculatePremiumResponse_firstName_empty.json");
    }

    @Test
    public void lastNameIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_lastName_empty.json",
                "rest/TravelCalculatePremiumResponse_lastName_empty.json");
    }

    @Test
    public void agreementDateFromIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_null.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_null.json");
    }

    @Test
    public void agreementDateToIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateTo_null.json",
                "rest/TravelCalculatePremiumResponse_dateTo_null.json");
    }

    @Test
    public void agreementDateFromAfterAgreementDateTo() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_after_dateTo.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_after_dateTo.json");
    }

    @Test
    public void agreementDateFromEqualsAgreementDateTo() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_equals_dateTo.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_equals_dateTo.json");
    }

    @Test
    public void agreementDateFromInPast() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_dateFrom_inPast.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_inPast.json");
    }

    @Test
    public void selectedRisksIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_selectedRisks_empty.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_empty.json");
    }

    @Test
    public void selectedRisksIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_selectedRisks_null.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_null.json");
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_allFields_notProvided.json",
                "rest/TravelCalculatePremiumResponse_allFields_notProvided.json");
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

        assertJson(jsonExpectedStringResponse)
                .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                .isEqualTo(jsonCurrentStringResponse);
    }
}

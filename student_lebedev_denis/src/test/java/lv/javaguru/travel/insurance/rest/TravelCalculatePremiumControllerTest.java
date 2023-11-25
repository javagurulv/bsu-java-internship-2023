package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void personFirstNameIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_PersonFirstNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_PersonFirstNameIsNull.json");
    }

    @Test
    public void personFirstNameIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_PersonFirstNameIsEmpty.json",
                "rest/TravelCalculatePremiumResponse_PersonFirstNameIsEmpty.json");
    }

    @Test
    public void personLastNameIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_PersonLastNameIsNull.json",
                "rest/TravelCalculatePremiumResponse_PersonLastNameIsNull.json");
    }

    @Test
    public void personLastNameIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_PersonLastNameIsEmpty.json",
                "rest/TravelCalculatePremiumResponse_PersonLastNameIsEmpty.json");
    }

    @Test
    public void agreementDateFromIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateFromIsNull.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateFromIsNull.json");
    }

    @Test
    public void agreementDateFromIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateFromIsEmpty.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateFromIsEmpty.json");
    }

    @Test
    public void agreementDateToIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateToIsNull.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateToIsNull.json");
    }

    @Test
    public void agreementDateToIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateToIsEmpty.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateToIsEmpty.json");
    }

    @Test
    public void agreementDateFromIsNotLessThanAgreementDateTo() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateFromIsNotLessThanAgreementDateTo.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateFromIsNotLessThanAgreementDateTo.json");
    }

    @Test
    public void allFieldsAreNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_allFieldsAreNull.json",
                "rest/TravelCalculatePremiumResponse_AllFieldsAreNull.json");
    }

    @Test
    public void noErrors() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_NoErrors.json",
                "rest/TravelCalculatePremiumResponse_NoErrors.json");
    }

    private void executeAndCompare(String requestFilePath, String responseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(requestFilePath);
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = jsonFileReader.readJsonFromFile(responseFilePath);
        assertEquals(objectMapper.readTree(jsonResponse), objectMapper.readTree(result.getResponse().getContentAsString()));
    }
}

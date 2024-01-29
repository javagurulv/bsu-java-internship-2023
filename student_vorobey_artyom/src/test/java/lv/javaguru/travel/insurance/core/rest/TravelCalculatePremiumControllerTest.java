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
        executeAndCompare("rest/ControllerTestRequest.json",
                "rest/ControllerTestResponse.json");
    }

    @Test
    public void shouldReturnErrorIfFirstNameIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/firstNameIsInvalidControllerRequest.json",
                "rest/firstNameIsInvalidControllerResponse.json");
    }

    @Test
    public void shouldReturnErrorIfLastNameIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/lastNameIsInvalidControllerRequest.json",
                "rest/lastNameIsInvalidControllerResponse.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/agreementDateFromIsInvalidControllerRequest.json",
                "rest/agreementDateFromIsInvalidControllerResponse.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateToIsInvalidRestControllerTest() throws Exception {
        executeAndCompare("rest/agreementDateToIsInvalidControllerRequest.json",
                "rest/agreementDateToIsInvalidControllerResponse.json");
    }

    @Test
    public void shouldReturnValidAgreementPriceControllerTest() throws Exception {
        executeAndCompare("rest/agreementPriceIsValidRestControllerTestRequest.json",
                "rest/agreementPriceIsValidRestControllerTestResponse.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromAfterAgreementDateToRestControllerTest() throws Exception {
        executeAndCompare("rest/agreementDateFromAfterAgreementDateRestControllerTestRequest.json",
                "rest/agreementDateFromAfterAgreementDateRestControllerTestResponse.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromEqualsAgreementDateToRestControllerTest() throws Exception {
        executeAndCompare("rest/agreementDateFromEqualsAgreementDateToRestControllerTestRequest.json",
                "rest/agreementDateFromEqualsAgreementDateToRestControllerTestResponse.json");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsInPastRestControllerTest() throws Exception {
        executeAndCompare("rest/agreementDateFromIsInPastRestControllerTestRequest.json",
                "rest/agreementDateFromIsInPastRestControllerTestResponse.json");
    }

    @Test
    public void shouldReturnErrorIfSelectedRisksIsInvalidControllerTest() throws Exception {
        executeAndCompare("rest/selectedRisksIsInvalidControllerTestRequest.json",
                "rest/selectedRisksIsInvalidControllerTestResponse.json");
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

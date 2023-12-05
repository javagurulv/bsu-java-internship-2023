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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void successfulResponse() throws Exception {
        getResponseAndCompare(
                "rest/СorrectRequest.json",
                "rest/CorrectResponse.json"
        );
    }

    @Test
    public void emptyFirstNameError() throws Exception {
        getResponseAndCompare(
                "rest/EmptyFirstNameRequest.json",
                "rest/EmptyFirstNameErrorResponse.json"
        );
    }

    @Test
    public void emptyLastNameError() throws Exception {
        getResponseAndCompare(
                "rest/EmptyLastNameRequest.json",
                "rest/EmptyLastNameErrorResponse.json"
        );
    }

    @Test
    public void emptyDateFromError() throws Exception {
        getResponseAndCompare(
                "rest/EmptyDateFromRequest.json",
                "rest/EmptyDateFromErrorResponse.json"
        );
    }

    @Test
    public void emptyDateToError() throws Exception {
        getResponseAndCompare(
                "rest/EmptyDateToRequest.json",
                "rest/EmptyDateToErrorResponse.json"
        );
    }

    @Test
    public void dateToLessThanDateFromError() throws Exception {
        getResponseAndCompare(
                "rest/DateToIsLessThanDateFromRequest.json",
                "rest/DateToIsLessThanDateFromErrorResponse.json"
        );
    }

    @Test
    public void dateFromIsInThePastError() throws Exception {
        getResponseAndCompare(
                "rest/DateFromIsInThePastRequest.json",
                "rest/DateFromIsInThePastErrorResponse.json"
        );
    }

    @Test
    public void dateToIsInThePastError() throws Exception {
        getResponseAndCompare(
                "rest/DateToIsInThePastRequest.json",
                "rest/DateToIsInThePastErrorResponse.json"
        );
    }
    @Test
    public void selectedRisksListIsEmptyError() throws Exception {
        getResponseAndCompare(
                "rest/SelectedRisksListIsEmptyRequest.json/",
                "rest/SelectedRisksListIsEmptyErrorResponse.json"
        );
    }

    @Test
    public void emptyCountryError() throws Exception {
        getResponseAndCompare(
                "rest/EmptyCountryRequest.json/",
                "rest/EmptyCountryErrorResponse.json"
        );
    }

    @Test
    public void emptyMedicalLimitLevelError() throws Exception {
        getResponseAndCompare(
                "rest/EmptyMedicalLimitLevelRequest.json/",
                "rest/EmptyMedicalLimitLevelErrorResponse.json"
        );
    }

    @Test
    public void unsupportedCountryError() throws Exception {
        getResponseAndCompare(
                "rest/UnsupportedCountryRequest.json/",
                "rest/UnsupportedCountryErrorResponse.json"
        );
    }

    @Test
    public void unsupportedMedicalLimitLevelError() throws Exception {
        getResponseAndCompare(
                "rest/UnsupportedMedicalLimitLevelRequest.json/",
                "rest/UnsupportedMedicalLimitLevelErrorResponse.json"
        );
    }
    @Test
    public void nothingProvided() throws Exception {
        getResponseAndCompare(
                "rest/NothingProvidedRequest.json",
                "rest/NothingProvidedErrorsResponse.json"
        );
    }
    @Test
    public void selectedRisksListIsNullError() throws Exception {
        getResponseAndCompare(
                "rest/SelectedRisksListIsNullRequest.json/",
                "rest/SelectedRisksListIsNullResponse.json"
        );
    }


    private void getResponseAndCompare(String jsonRequestFilePath,
                                       String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);
        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }

}
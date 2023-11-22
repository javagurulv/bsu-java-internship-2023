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

    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    @Test
    public void successRequest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }

    @Test
    public void personFirstNameIsNull() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_personFirstName_is_null.json",
                "rest/TravelCalculatePremiumResponse_personFirstName_is_null.json"
        );
    }

    @Test
    public void personFirstNameIsEmpty() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_personFirstName_is_empty.json",
                "rest/TravelCalculatePremiumResponse_personFirstName_is_empty.json"
        );
    }

    @Test
    public void personLastNameIsNull() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_personLastName_is_null.json",
                "rest/TravelCalculatePremiumResponse_personLastName_is_null.json"
        );
    }

    @Test
    public void personLastNameIsEmpty() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_personLastName_is_empty.json",
                "rest/TravelCalculatePremiumResponse_personLastName_is_empty.json"
        );
    }

    @Test
    public void agreementDateFromNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    public void agreementDateToNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    public void agreementDateFromIsInThePast() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_in_the_past.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_in_the_past.json"
        );
    }

    @Test
    public void agreementDateToIsInThePast() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_in_the_past.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_in_the_past.json"
        );
    }

    @Test
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    public void selectedRisksIsNull() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_selectedRisks_null.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_null.json"
        );
    }

    @Test
    public void selectedRisksIsEmpty() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_selectedRisks_empty.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_empty.json"
        );
    }

    @Test
    public void selectedRisksNotSupported() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_selectedRisks_not_supported.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_not_supported.json"
        );
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }

    private void executeAndCompare(String jsonRequestFilePath,
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

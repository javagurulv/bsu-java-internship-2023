package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired private JsonFileToString jsonReader;

    private void executeAndCompare(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonReader.readJsonFromFile(jsonRequestFilePath);
        String jsonResponse = jsonReader.readJsonFromFile(jsonResponseFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void simpleRestControllerTest_normal() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_normal.json",
                "rest/TravelCalculatePremiumResponse_normal.json"
        );
    }
    @Test
    public void simpleRestControllerTest_firstNameField_Is_Blank() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_firstNameField_Is_Blank.json",
                "rest/TravelCalculatePremiumResponse_firstNameField_Is_Blank.json"
        );
    }
    @Test
    public void simpleRestControllerTest_lastNameField_Is_Blank() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_lastNameField_Is_Blank.json",
                "rest/TravelCalculatePremiumResponse_lastNameField_Is_Blank.json"
        );
    }
    @Test
    public void simpleRestControllerTest_agreementDateFromField_Is_Blank() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFromField_Is_Blank.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFromField_Is_Blank.json"
        );
    }
    @Test
    public void simpleRestControllerTest_agreementDateToField_Is_Blank() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateToField_Is_Blank.json",
                "rest/TravelCalculatePremiumResponse_agreementDateToField_Is_Blank.json"
        );
    }
    @Test
    public void simpleRestControllerTest_AllFields_Are_Blank() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_AllFields_Are_Blank.json",
                "rest/TravelCalculatePremiumResponse_AllFields_Are_Blank.json"
        );
    }
    @Test
    public void simpleRestControllerTest_DateTo_Is_Less_DateFrom() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_DateTo_Is_Less_DateFrom.json",
                "rest/TravelCalculatePremiumResponse_DateTo_Is_Less_DateFrom.json"
        );
    }
}

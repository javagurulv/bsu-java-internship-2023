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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileService reader;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void controllerTestWithoutErrors() throws Exception {
       simpleRestControllerTest(
               "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestWithoutErrors.json",
               "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseWithoutErrors.json"
       );
    }
    @Test
    public void controllerTestEmptyDateFrom() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestEmptyDateFrom.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseEmptyDateFrom.json"
        );
    }
    @Test
    public void controllerTestEmptyDateTo() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestEmptyDateTo.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseEmptyDateTo.json"
        );
    }
    @Test
    public void controllerTestEmptyFirstName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestEmptyFirstName.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseEmptyFirstName.json"
        );
    }
    @Test
    public void controllerTestEmptyLastName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestEmptyLastName.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseEmptyLastName.json"
        );
    }
    @Test
    public void controllerTestEmptyNullFirstName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestNullFirstName.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseNullFirstName.json"
        );
    }
    @Test
    public void controllerTestEmptyNullLastName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestNullLastName.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseNullLastName.json"
        );
    }
    @Test
    public void controllerTestEmptyWrongDateDifferance() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestWrongDateDifferance.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseWrongDateDifferance.json"
        );
    }
    @Test
    public void controllerTestEmptySelectedRisk() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequest_JsonFiles/TravelCalculatePremiumRequestEmptySelectedRisk.json",
                "rest/TravelCalculatePremiumResponse_JsonFiles/TravelCalculatePremiumResponseEmptySelectedRisk.json"
        );
    }
    private void simpleRestControllerTest(String requestPath, String responsePath) throws Exception {
        String requestJson = reader.readJsonFile(requestPath);
        String responseJson = reader.readJsonFile(responsePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertJson(responseJson)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseBody);
    }

}

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
               "rest/TravelCalculatePremiumRequestWithoutErrors.json",
               "rest/TravelCalculatePremiumResponseWithoutErrors.json"
       );
    }
    @Test
    public void controllerTestEmptyDateFrom() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestEmptyDateFrom.json",
                "rest/TravelCalculatePremiumResponseEmptyDateFrom.json"
        );
    }
    @Test
    public void controllerTestEmptyDateTo() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestEmptyDateTo.json",
                "rest/TravelCalculatePremiumResponseEmptyDateTo.json"
        );
    }
    @Test
    public void controllerTestEmptyEmptyFirstName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestEmptyFirstName.json",
                "rest/TravelCalculatePremiumResponseEmptyFirstName.json"
        );
    }
    @Test
    public void controllerTestEmptyEmptyLastName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestEmptyLastName.json",
                "rest/TravelCalculatePremiumResponseEmptyLastName.json"
        );
    }
    @Test
    public void controllerTestEmptyNullFirstName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestNullFirstName.json",
                "rest/TravelCalculatePremiumResponseNullFirstName.json"
        );
    }
    @Test
    public void controllerTestEmptyNullLastName() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestNullLastName.json",
                "rest/TravelCalculatePremiumResponseNullLastName.json"
        );
    }
    @Test
    public void controllerTestEmptyWrongDateDifferance() throws Exception {
        simpleRestControllerTest(
                "rest/TravelCalculatePremiumRequestWrongDateDifferance.json",
                "rest/TravelCalculatePremiumResponseWrongDateDifferance.json"
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
        assertEquals(mapper.readTree(responseBody), mapper.readTree(responseJson));
    }

}

package lv.javaguru.travel.insurance.rest;


import com.fasterxml.jackson.databind.JsonNode;
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

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonReader reader;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void normalRestControllerTest() throws Exception {
        performTest("rest/Request.json",
                   "rest/Response.json");
    }

    @Test
    public void withoutFirstNameTest() throws Exception {
        performTest("rest/RequestWithoutFirstName.json",
                "rest/ResponseWithoutFirstName.json");
    }

    @Test
    public void withoutLastNameTest() throws Exception{
        performTest("rest/RequestWithoutLastName.json",
                "rest/ResponseWithoutLastName.json");
    }

    @Test
    public void withoutAgreementDateTo() throws Exception {
        performTest("rest/RequestWithoutAgreementDateTo.json",
                "rest/ResponseWithoutAgreementDateTo.json");
    }

    @Test
    public void withoutAgreementDateFrom() throws Exception {
        performTest("rest/RequestWithoutAgreementDateFrom.json",
                "rest/ResponseWithoutAgreementDateFrom.json");
    }

    @Test
    public  void dateToLessFromTest() throws Exception {
        performTest("rest/RequestDateToLessFrom.json",
                "rest/ResponseDateToLessFrom.json");
    }

    @Test
    public void dateFromLessCurrentDate() throws Exception {
        performTest("rest/RequestDateFromLessCurrentDate.json",
                "rest/ResponseDateFromLessCurrentDate.json");
    }

    @Test
    public void dateFromLessCurrentDateAndDateToLessDateFrom() throws Exception {
        performTest("rest/RequestDateFromLessCurrentDateAndDateToLessDateFrom.json",
                "rest/ResponseDateFromLessCurrentDateAndDateToLessDateFrom.json");
    }

    public void performTest(String requestPath, String responsePath) throws Exception {
        String request = reader.readJson(requestPath);

        MvcResult res = mockMvc.perform(post("/insurance/travel/")
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = reader.readJson(responsePath);
        String response = res.getResponse().getContentAsString();
        assertEquals(mapper.readValue(response, JsonNode.class), mapper.readValue(expectedResponse, JsonNode.class));
    }
}

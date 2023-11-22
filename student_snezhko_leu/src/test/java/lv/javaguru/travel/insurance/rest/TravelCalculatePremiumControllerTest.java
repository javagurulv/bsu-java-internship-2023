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

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void ControllerAllParametersIsCorrectTest() throws Exception {//throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/TravelCalculatePremiumRequest.json";
        String pathResponse = "rest/TravelCalculatePremiumResponse.json";

        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = JsonFileReader.readJsonFile("rest/TravelCalculatePremiumResponse.json");
        assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
    @Test
    public void ControllerWithoutFirstNameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumRequestWithoutFirstName.json";
        String pathResponse = "rest/PremiumResponseWithoutFirstName.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = JsonFileReader.readJsonFile(pathResponse);
        assertEquals(mapper.readTree(responseFromRequest), mapper.readTree(responseFromFile));
    }
    @Test
    public void ControllerWithoutLastNameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumRequestWithoutLastName.json";
        String pathResponse = "rest/PremiumResponseWithoutLastName.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = JsonFileReader.readJsonFile(pathResponse);
        assertEquals(mapper.readTree(responseFromRequest), mapper.readTree(responseFromFile));
    }
    @Test
    public void ControllerWithoutDateFromAndDateToTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumRequestWithoutDateFromAndDateTo.json";
        String pathResponse = "rest/PremiumResponseWithoutDateFromAndDateTo.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = JsonFileReader.readJsonFile(pathResponse);
        assertEquals(mapper.readTree(responseFromRequest), mapper.readTree(responseFromFile));
    }
    private String getResponseFromRequest(String pathRequest) throws Exception{
        MvcResult result = mockMvc.perform(
                        post("/insurance/travel/")
                                .content(JsonFileReader.readJsonFile(pathRequest))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString();
    }
}

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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonReader jsonReader;
    private ObjectMapper mapper = new ObjectMapper();

    public void compare(String requestPath, String responsePath) throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonReader.readJsonToString(requestPath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(mapper.readTree(result.getResponse().getContentAsString()),
                mapper.readTree(jsonReader.readJsonToString(responsePath)));
    }

    @Test
    public void everythingProvided() throws Exception {
        compare("rest/TravelCalculatePremiumRequest.json", "rest/TravelCalculatePremiumResponse.json");
    }

    @Test
    public void firstNameNotProvided() throws Exception{
        compare("rest/FirstNameNotProvidedRequest.json", "rest/FirstNameNotProvidedResponse.json");
    }

    @Test
    public void lastNameNotProvided() throws Exception{
        compare("rest/LastNameNotProvidedRequest.json", "rest/LastNameNotProvidedResponse.json");
    }

    @Test
    public void dateToNotProvided() throws Exception{
        compare("rest/DateToNotProvidedRequest.json", "rest/DateToNotProvidedResponse.json");
    }

    @Test
    public void DateFromNotProvided() throws Exception{
        compare("rest/DateFromNotProvidedRequest.json", "rest/DateFromNotProvidedResponse.json");
    }

    @Test
    public void dateToLessThanDateFrom() throws Exception{
        compare("rest/DateToLessThanDateFromRequest.json", "rest/DateToLessThanDateFromResponse.json");
    }
}
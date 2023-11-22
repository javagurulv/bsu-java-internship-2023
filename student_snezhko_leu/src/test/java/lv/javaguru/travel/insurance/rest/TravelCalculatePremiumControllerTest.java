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
    public void readedJsonControllerTest() throws Exception {//throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        //assertEquals(mapper.readTree(JsonFileReader.readJsonFile("../../resources/rest/TravelCalculatePremiumRequest.json")),)
        MvcResult result = mockMvc.perform(
                        post("/insurance/travel/")
                                .content(JsonFileReader.readJsonFile("rest/TravelCalculatePremiumResponse.json"))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseFromRequest = result.getResponse().getContentAsString();
        String responseFromFile = JsonFileReader.readJsonFile("rest/TravelCalculatePremiumResponse.json");
        assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }

}

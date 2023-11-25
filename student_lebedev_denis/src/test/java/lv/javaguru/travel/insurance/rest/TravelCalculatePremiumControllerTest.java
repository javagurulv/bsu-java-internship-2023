package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void simpleRestControllerTest() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest.json",
                "rest/TravelCalculatePremiumResponse.json");
    }

    private void executeAndCompare(String requestFilePath, String responseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(requestFilePath);
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = jsonFileReader.readJsonFromFile(responseFilePath);
        assertEquals(objectMapper.readTree(jsonResponse), objectMapper.readTree(result.getResponse().getContentAsString()));
    }
}

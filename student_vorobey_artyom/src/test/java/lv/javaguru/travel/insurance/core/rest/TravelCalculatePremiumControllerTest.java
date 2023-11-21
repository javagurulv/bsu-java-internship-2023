package lv.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpHeaders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void simpleRestControllerTest() throws Exception {
        executeAndCompare("rest/ControllerTestRequest.json",
                "rest/ControllerTestResponse.json");
    }

    private void executeAndCompare(String JsonFileRequestPath, String JsonFileResponsePath) throws Exception {
        String jsonStringRequest = jsonFileReader.readJsonFromFile(JsonFileRequestPath);
        String jsonExpectedStringResponse = jsonFileReader.readJsonFromFile(JsonFileResponsePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                .content(jsonStringRequest)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String jsonCurrentStringResponse = result.getResponse().getContentAsString();

        assertEquals(mapper.readTree(jsonExpectedStringResponse), mapper.readTree(jsonCurrentStringResponse));
    }
}

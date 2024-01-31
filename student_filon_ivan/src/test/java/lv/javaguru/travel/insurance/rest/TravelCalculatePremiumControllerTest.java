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

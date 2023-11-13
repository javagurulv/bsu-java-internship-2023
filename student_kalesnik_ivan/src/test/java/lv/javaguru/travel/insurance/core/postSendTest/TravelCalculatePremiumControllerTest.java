package lv.javaguru.travel.insurance.core.postSendTest;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void simpleRestControllerTest() throws Exception {
        finishComparing("TravelCalculatePremiumRequest.json", "TravelCalculatePremiumResponse.json");
    }

    private void finishComparing(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception {
        String jsonRequest = readJsonFromFile(jsonRequestFilePath);
        String jsonResponse = performPostRequest(jsonRequest);
        compareResponseWithExpected(jsonResponse, jsonResponseFilePath);
    }

    private String readJsonFromFile(String filePath) throws IOException {
        return jsonFileReader.readJsonFromFile(filePath);
    }

    private String performPostRequest(String jsonRequest) throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        return result.getResponse().getContentAsString();
    }

    private void compareResponseWithExpected(String response, String expectedResponseFilePath) throws IOException {
        String expectedResponse = readJsonFromFile(expectedResponseFilePath);
        assertEquals(mapper.readTree(response), mapper.readTree(expectedResponse));
    }


}

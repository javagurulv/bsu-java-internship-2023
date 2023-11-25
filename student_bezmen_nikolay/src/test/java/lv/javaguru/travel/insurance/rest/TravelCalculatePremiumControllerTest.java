package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private readDataFromJsonFile readJson;
    @Autowired private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTest() throws Exception {
        compareResponseFromRequest(
                "rest/TravelCalculatePremiumRequest.json",
                "rest/TravelCalculatePremiumResponse.json"
        );
    }

    private void compareResponseFromRequest(String pathToRequestJsonFile,
                                                   String pathToCorrectResponseJsonFile) throws Exception {
        String jsonRequestToString = readJson.parseData(pathToRequestJsonFile);

        MvcResult resultOfResponse = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequestToString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseToString = resultOfResponse.getResponse().getContentAsString();

        String correctResponse = readJson.parseData(pathToCorrectResponseJsonFile);

        JSONAssert.assertEquals(correctResponse,responseToString, false);
    }

}
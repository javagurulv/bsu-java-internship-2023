package lv.javaguru.travel.insurance.core.rest;

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

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void TestWhenAllIsOkay() throws Exception {
        compareResponses("jsonRestFiles/TravelCalculatePremiumRequest.json",
                "jsonRestFiles/TravelCalculatePremiumResponse.json");
    }

    @Test
    public void TestWhenDateFromIsNull() throws Exception{
        compareResponses("jsonRestFiles/RequestWhenDateFromIsNull.json",
                "jsonRestFiles/ResponseWhenDateFromIsNull.json");
    }

    @Test
    public void TestWhenDateToIsNull() throws Exception{
        compareResponses("jsonRestFiles/RequestWhenDateToIsNull.json",
                "jsonRestFiles/ResponseWhenDateToIsNull.json");
    }

    @Test
    public void TestWhenFirstNameIsNull() throws Exception{
        compareResponses("jsonRestFiles/RequestWhenFirstNameIsNull.json",
                "jsonRestFiles/ResponseWhenFirstNameIsNull.json");
    }

    @Test
    public void TestWhenLastNameIsNull() throws Exception{
        compareResponses("jsonRestFiles/RequestWhenLastNameIsNull.json",
                "jsonRestFiles/ResponseWhenLastNameIsNull.json");
    }

    @Test
    public void TestWhenDateToLessThanDateFrom() throws Exception{
        compareResponses("jsonRestFiles/RequestWhenDateToLessThanDateFrom.json",
                "jsonRestFiles/ResponseWhenDateToLessThanDateFrom.json");
    }

    @Test
    public void TestWhenDatesInPast() throws Exception{
        compareResponses("jsonRestFiles/RequestWhenDatesInPast.json",
                "jsonRestFiles/ResponseWhenDatesInPast.json");
    }

    private void compareResponses(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/insurance/travel/")
                        .content(JsonFileReader.readFile(jsonRequestFilePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String requestData = mvcResult.getResponse().getContentAsString();
        String response = JsonFileReader.readFile(jsonResponseFilePath);
        assertEquals(objectMapper.readTree(requestData), objectMapper.readTree(response));
    }
}

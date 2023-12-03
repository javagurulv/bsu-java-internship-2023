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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader JsonReader;
    private ObjectMapper OMapper = new ObjectMapper();

    @Test
    public void EmptyFirstNameTest() throws Exception
    {
        compare("rest/EmptyFirstNameRequest.json",
                "rest/EmptyFirstNameResponse.json");
    }
    @Test
    public void EmptyLastNameTest() throws Exception
    {
        compare("rest/EmptyLastNameRequest.json",
                "rest/EmptyLastNameResponse.json");
    }
    @Test
    public void NullFirstNameTest() throws Exception
    {
        compare("rest/NullFirstNameRequest.json",
                "rest/NullFirstNameResponse.json");
    }
    @Test
    public void NullLastNameTest() throws Exception
    {
        compare("rest/NullLastNameRequest.json",
                "rest/NullLastNameResponse.json");
    }
    @Test
    public void NullAgreementDateFromTest() throws Exception
    {
        compare("rest/NullAgreementDateFromRequest.json",
                "rest/NullAgreementDateFromResponse.json");
    }
    @Test
    public void NullAgreementDateToTest() throws Exception
    {
        compare("rest/NullAgreementDateToRequest.json",
                "rest/NullAgreementDateToResponse.json");
    }
    @Test
    public void DateFromBiggerThanDateToTest() throws Exception
    {
        compare("rest/DateFromBiggerThanDateToRequest.json",
                "rest/DateFromBiggerThanDateToResponse.json");
    }
    @Test
    public void PastAgreementDateFromTest() throws Exception
    {
        compare("rest/PastAgreementDateFromRequest.json",
                "rest/PastAgreementDateFromResponse.json");
    }
    @Test
    public void PastAgreementDateToTest() throws Exception
    {
        compare("rest/PastAgreementDateToRequest.json",
                "rest/PastAgreementDateToResponse.json");
    }
    @Test
    public void ValidTest() throws Exception
    {
        compare("rest/ValidTestRequest.json",
                "rest/ValidTestResponse.json");
    }

    private void compare(String RequestFilePath,
                         String ResponseFilePath) throws Exception {
        String RequestJson = JsonReader.ReadJsonFileInString(RequestFilePath);
        String ResponseJson = JsonReader.ReadJsonFileInString(ResponseFilePath);
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                .content(RequestJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(OMapper.readTree(ResponseJson), OMapper.readTree(result.getResponse().getContentAsString()));
    }
}

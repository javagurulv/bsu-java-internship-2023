package lv.javaguru.travel.insurance.rest;

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

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    @Test
    public void simpleRestControllerTestUpgraded() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequest.json",
                "rest/TravelCalculatePremiumResponse.json");
    }

    @Test
    public void simpleRestControllerFirstNameIsEmptyTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithoutFirstName.json",
                "rest/TravelCalculatePremiumResponseWithoutFirstName.json");
    }

    @Test
    public void simpleRestControllerLastNameIsEmptyTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithoutLastName.json",
                "rest/TravelCalculatePremiumResponseWithoutLastName.json");
    }

    @Test
    public void simpleRestControllerDateFromIsEmptyTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithoutDateFrom.json",
                "rest/TravelCalculatePremiumResponseWithoutDateFrom.json");
    }

    @Test
    public void simpleRestControllerDateToIsEmptyTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithoutDateTo.json",
                "rest/TravelCalculatePremiumResponseWithoutDateTo.json");
    }

    @Test
    public void simpleRestControllerDateToEarlierDateFromTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithErrorDateToEarlierDateFrom.json",
                "rest/TravelCalculatePremiumResponseWithErrorDateToEarlierDateFrom.json");
    }

    @Test
    public void simpleRestControllerDateFromInThePastTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithErrorDateFromInThePast.json",
                "rest/TravelCalculatePremiumResponseWithErrorDateFromInThePast.json");
    }

    @Test
    public void simpleRestControllerDateToInThePastTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithErrorDateToInThePast.json",
                "rest/TravelCalculatePremiumResponseWithErrorDateToInThePast.json");
    }

    @Test
    public void simpleRestControllerDateFromAndDateToInThePastTest() throws Exception{
        compareRequestAndResponse("rest/TravelCalculatePremiumRequestWithErrorDateFromAndDateToInThePast.json",
                "rest/TravelCalculatePremiumResponseWithErrorDateFromAndDateToInThePast.json");
    }

    @Test
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Nastya\", \n" +
                                "\"personLastName\" : \"Larionova\", \n" +
                                "\"agreementDateFrom\" : \"2029-05-25\",\n" +
                                "\"agreementDateTo\" : \"2029-05-29\"\n" +
                                "}")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Nastya")))
                .andExpect(jsonPath("personLastName", is("Larionova")))
                .andExpect(jsonPath("agreementDateFrom", is("2029-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2029-05-29")))
                .andExpect(jsonPath("agreementPrice", is(4)))
                .andReturn();
    }

    private void compareRequestAndResponse(String pathRequestJson, String pathResponseJson) throws Exception{
        String requestJson = jsonFileReader.readJsonFile(pathRequestJson);
        String responseJson = jsonFileReader.readJsonFile(pathResponseJson);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        assertEquals(responseBodyContent, responseJson); // TODO сравнение содержимого без учёта порядка внутренних полей
    }
}
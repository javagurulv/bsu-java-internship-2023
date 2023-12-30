package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
//import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
//import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.TravelUnderwriting;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest//(classes = {TravelCalculatePremiumServiceImpl.class})
@AutoConfigureMockMvc
//@WebMvcTest(JsonFileReader.class)
public class TravelCalculatePremiumControllerTest {
    @Autowired private MockMvc mockMvc;

 //   ModelAssert assert;
    private JsonFileReader jsonFileReader = new JsonFileReader(); //= new JsonFileReader();
    //    private MockMvc mockMvc;
/*
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Mock
    TravelUnderwriting underwriting;

 */

    @Test
    public void ControllerAllParametersIsCorrectTest() throws Exception {//throws FileNotFoundException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/TravelCalculatePremiumRequest.json";
        String pathResponse = "rest/TravelCalculatePremiumResponse.json";

        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile("rest/TravelCalculatePremiumResponse.json");
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //when(underwriting.calculatePremium(TravelCalculatePremiumResponse.class)).thenReturn()
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }

    @Test
    public void ControllerWithoutFirstNameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumWithoutFirstNameRequest.json";
        String pathResponse = "rest/PremiumWithoutFirstNameResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
    @Test
    public void ControllerWithoutLastNameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumWithoutLastNameRequest.json";
        String pathResponse = "rest/PremiumWithoutLastNameResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
    @Test
    public void ControllerWithoutDateFromAndDateToTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumWithoutDateFromAndDateToRequest.json";
        String pathResponse = "rest/PremiumWithoutDateFromAndDateToResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
    @Test
    public void ControllerWithoutRisksTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumWithoutRisksRequest.json";
        String pathResponse = "rest/PremiumWithoutRisksResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
    @Test
    public void ControllerWithNullRisksTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/PremiumWithNullRisksRequest.json";
        String pathResponse = "rest/PremiumWithNullRisksResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }


    private String getResponseFromRequest(String pathRequest) throws Exception{
        MvcResult result = mockMvc.perform(
                        post("/insurance/travel/")
                                .content(jsonFileReader.readJsonFile(pathRequest))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString();
    }
}

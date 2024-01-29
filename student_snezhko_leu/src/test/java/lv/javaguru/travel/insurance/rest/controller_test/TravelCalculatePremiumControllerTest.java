package lv.javaguru.travel.insurance.rest.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
//import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
//import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
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
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest//(classes = {TravelCalculatePremiumServiceImpl.class})
@AutoConfigureMockMvc
//@WebMvcTest(JsonFileReader.class)
public class TravelCalculatePremiumControllerTest {
    @Autowired protected MockMvc mockMvc;

    private static final String BASE_URL = "/insurance/travel/api/";
 //   ModelAssert assert;
    protected JsonFileReader jsonFileReader = new JsonFileReader(); //= new JsonFileReader();
    //    private MockMvc mockMvc;
/*
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Mock
    TravelUnderwriting underwriting;

 */
/*
    @Test
    public void ControllerAllParametersIsCorrectTest() throws Exception {//throws FileNotFoundException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/test_case_1/TravelCalculatePremiumRequest.json";
        String pathResponse = "rest/test_case_1/TravelCalculatePremiumResponse.json";

        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //when(underwriting.calculatePremium(TravelCalculatePremiumResponse.class)).thenReturn()
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }

 */
/*
    @Test
    public void ControllerWithoutFirstNameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/test_case_2/PremiumWithoutFirstNameRequest.json";
        String pathResponse = "rest/test_case_2/PremiumWithoutFirstNameResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }
    */
  /*
    @Test
    public void ControllerWithoutLastNameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String pathRequest = "rest/test_case_3/PremiumWithoutLastNameRequest.json";
        String pathResponse = "rest/test_case_3/PremiumWithoutLastNameResponse.json";
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
        String pathRequest = "rest/test_case_4/PremiumWithoutDateFromAndDateToRequest.json";
        String pathResponse = "rest/test_case_4/PremiumWithoutDateFromAndDateToResponse.json";
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
        String pathRequest = "rest/test_case_6/PremiumWithoutRisksRequest.json";
        String pathResponse = "rest/test_case_6/PremiumWithoutRisksResponse.json";
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
        String pathRequest = "rest/test_case_5/PremiumWithNullRisksRequest.json";
        String pathResponse = "rest/test_case_5/PremiumWithNullRisksResponse.json";
        String responseFromRequest = getResponseFromRequest(pathRequest);
        String responseFromFile = jsonFileReader.readJsonFile(pathResponse);
        assertJson(responseFromRequest)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseFromFile);
        //assertEquals(mapper.readTree(responseFromFile), mapper.readTree(responseFromRequest));
    }

*/
    protected String getResponseFromRequest(String pathRequest) throws Exception{
        MvcResult result = mockMvc.perform(
                        post(BASE_URL)
                                .content(jsonFileReader.readJsonFile(pathRequest))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString();
    }
}

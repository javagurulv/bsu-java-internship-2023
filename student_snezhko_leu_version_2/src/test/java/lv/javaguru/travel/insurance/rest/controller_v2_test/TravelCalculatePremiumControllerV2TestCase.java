package lv.javaguru.travel.insurance.rest.controller_v2_test;

import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static lv.javaguru.travel.insurance.rest.RemoveRandomValues.removeRandomValues;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerV2TestCase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    private static final String BASE_URL = "/insurance/travel/api/v2/";

    protected abstract String getTestCaseFolderPath();

    protected void executeAndCompare(String testCaseFolderName) throws Exception{
        executeAndCompare(
                "rest/v2/" + getTestCaseFolderPath() + testCaseFolderName + "/request.json",
                "rest/v2/" + getTestCaseFolderPath() + testCaseFolderName + "/response.json"
        );
    }

    protected void executeAndCompare(String jsonRequestFilePath,
                                     String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post(BASE_URL)
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();



        String responseBodyContent = removeRandomValues(result.getResponse().getContentAsString());

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);


        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }
}

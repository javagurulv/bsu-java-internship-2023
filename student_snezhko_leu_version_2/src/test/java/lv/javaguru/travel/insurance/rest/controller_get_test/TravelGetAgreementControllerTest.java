package lv.javaguru.travel.insurance.rest.controller_get_test;

import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelGetAgreementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    private final String BASE_URL = "/insurance/travel/api/internal/agreement";
    protected abstract String getTestCaseName();

    protected abstract String getTestUuid();

    private String getURL() {
        return BASE_URL + "/" + getTestUuid();
    }

    public void executeAndCompare() throws Exception {
        executeAndCompare("rest/get/" + getTestCaseName() + "/request.json",
                "rest/get/"+getTestCaseName() + "/response.json");
    }

    protected void executeAndCompare(String requestPath, String responsePath) throws Exception{
        String requestJson = jsonFileReader.readJsonFromFile(requestPath);

        MvcResult result = mockMvc.perform(
                get(getURL())
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        String expectedResponse = jsonFileReader.readJsonFromFile(responsePath);

        assertJson(responseJson)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponse);
    }
}

package lv.javaguru.travel.insurance.core.rest;

import lv.javaguru.travel.insurance.utils.JsonReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private static Stream<Map.Entry<String,String>> jsons() {
        return Stream.of(
                new AbstractMap.SimpleEntry<>("src/test/resources/correctRequestWithAllArgs.json",
                        "src/test/resources/correctResponseWithAllArgs.json"),
                new AbstractMap.SimpleEntry<>("src/test/resources/requestWithoutagreementDateFrom.json",
                        "src/test/resources/responseWithoutAgreementDateFrom.json"),
                new AbstractMap.SimpleEntry<>("src/test/resources/requestWithoutagreementDateTo.json",
                        "src/test/resources/responseWithoutAgreementDateTo.json"),
                new AbstractMap.SimpleEntry<>("src/test/resources/requestWithoutAllArgs.json",
                        "src/test/resources/responseWithoutAllArgs.json"),
                new AbstractMap.SimpleEntry<>("src/test/resources/requestWithoutPersonFirstName.json",
                        "src/test/resources/responseWithoutPersonFirstName.json"),
                new AbstractMap.SimpleEntry<>("src/test/resources/requestWithoutPersonLastName.json",
                        "src/test/resources/responseWithoutPersonLastName.json")
        );
    }


    @ParameterizedTest
    @MethodSource("jsons")
    public void simpleRestControllerTest(Map.Entry<String, String> jsonEntry) throws Exception {
        String correctJsonRequestPath = jsonEntry.getKey();
        String correctJsonResponsePath = jsonEntry.getValue();

        String correctJsonRequest = JsonReader.readJson(correctJsonRequestPath);
        String correctJsonResponse = JsonReader.readJson(correctJsonResponsePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(correctJsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        assertTrue(JsonReader.compareJsonString(correctJsonResponse, responseBodyContent));

        assertJson(correctJsonResponse)
                .where().keysInAnyOrder()
                .isEqualTo(responseBodyContent);


    }



}
package lv.javaguru.travel.insurance.core.rest;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.rest.RestConfig;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelPremiumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void okTest() throws Exception {
        mockMvc.perform(post(RestConfig.insuranceTravel + "/")
                        .content("{" +
                                "\"personFirstName\" : \"p1\",\n" +
                                "\"personLastName\" : \"p2\",\n" +
                                "\"agreementDateFrom\" : \"2020-10-10\",\n" +
                                "\"agreementDateTo\" : \"2020-10-11\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("p1")))
                .andExpect(jsonPath("personLastName", is("p2")))
                .andExpect(jsonPath("agreementDateFrom", is("2020-10-10")))
                .andExpect(jsonPath("agreementDateTo", is("2020-10-11")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andReturn();
    }

    @Test
    public void wrongDateTest() throws Exception {
        var errorJsonArray = new JSONArray();
        JSONObject object = new JSONObject(Map.of("field", "agreementDayFrom", "error", "Must be before agreementDayTo"));

        mockMvc.perform(post(RestConfig.insuranceTravel + "/")
                        .content("{" +
                                "\"personFirstName\" : \"p1\",\n" +
                                "\"personLastName\" : \"p2\",\n" +
                                "\"agreementDateFrom\" : \"2020-10-10\",\n" +
                                "\"agreementDateTo\" : \"2020-10-09\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", nullValue()))
                .andExpect(jsonPath("personLastName", nullValue()))
                .andExpect(jsonPath("agreementDateFrom", nullValue()))
                .andExpect(jsonPath("agreementDateTo", nullValue()))
                .andExpect(jsonPath("agreementPrice", nullValue()))
                .andExpect(jsonPath("errorList", hasItem(object)))
                .andReturn();
    }

}

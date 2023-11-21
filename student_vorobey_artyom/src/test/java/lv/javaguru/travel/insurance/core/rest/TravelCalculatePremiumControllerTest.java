package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.http.HttpHeaders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Artyom\",\n" +
                                "\"personLastName\" : \"Vorobey\",\n" +
                                "\"agreementDateFrom\" : \"2023-10-19\",\n" +
                                "\"agreementDateTo\" : \"2023-10-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Artyom")))
                .andExpect(jsonPath("personLastName", is("Vorobey")))
                .andExpect(jsonPath("agreementDateFrom", is("2023-10-19")))
                .andExpect(jsonPath("agreementDateTo", is("2023-10-20")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andReturn();
    }
}

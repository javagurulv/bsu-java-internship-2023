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
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vasja")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(4)))
                .andReturn();
    }

}

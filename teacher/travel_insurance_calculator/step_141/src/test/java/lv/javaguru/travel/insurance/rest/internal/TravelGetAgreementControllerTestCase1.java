package lv.javaguru.travel.insurance.rest.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.rest.common.JsonFileReader;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelGetAgreementControllerTestCase1 extends TravelGetAgreementControllerTestCase {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    @Test
    public void shouldGetAgreement() throws Exception {
        String uuid = calculateAgreementAndGetUuid();
        executeAndCompare(uuid, true);
    }

    private String calculateAgreementAndGetUuid() throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/internal/test_case_1/calculate_premium_request.json");

        MvcResult result = mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        TravelCalculatePremiumResponseV2 response = new ObjectMapper().readValue(responseBodyContent, TravelCalculatePremiumResponseV2.class);

        return response.getUuid();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "internal/test_case_1";
    }

}

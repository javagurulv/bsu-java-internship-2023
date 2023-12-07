package lv.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.TestFileManager;
import lv.javaguru.travel.insurance.rest.RestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelPremiumControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired TestFileManager fileManager;

    @Autowired
    private MockMvc mockMvc;

    static final String testRestJSONFilePath = "rest/";
    static final String requestFileName = "/request.json";
    static final String responseFileName = "/response.json";


    @Test
    public void allOkFromFile() throws Exception {
        compareData("allOk");
    }

    @Test
    public void mandatoryFieldsMissing() throws Exception {
        compareData("mandatoryFieldsMissing");
    }
    @Test
    public void oneMandatoryFieldMissing() throws Exception {
        compareData("oneFieldMissing");
    }

    @Test
    public void nullRisks() throws Exception {
        compareData("nullRisks");
    }

    @Test
    public void emptyRisks() throws Exception {
        compareData("emptyRisks");
    }

    void compareData(String folder) throws Exception {
        String requestFilePath = testRestJSONFilePath + folder + requestFileName;
        String responseFilePath = testRestJSONFilePath + folder + responseFileName;

        String responseString = mockMvc
                .perform(post(RestConfig.insuranceTravel + "/")
                        .content(fileManager.openFile(requestFilePath))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        String responseRealString = fileManager.openFile(responseFilePath);

        assertJson(responseRealString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseString);
    }

}

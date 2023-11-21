package lv.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.file.manager.TestFileManager;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.rest.RestConfig;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelPremiumControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    static final String testRestJSONFilePath = "rest/";
    static final String requestFileName = "/request.json";
    static final String responseFileName = "/response.json";
    private TestFileManager fileManager = new TestFileManager();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allOkFromFile() throws Exception {
        compareData("allOk");
    }

    @Test
    public void wrongDateDistance() throws Exception {
        compareData("wrongDateDistance");
    }

    @Test
    public void mandatoryFieldsMissing() throws Exception {
        compareData("mandatoryFieldsMissing");
    }

    @Test
    public void oneFieldMissing() throws Exception {
        compareData("oneFieldMissing");
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

        assertEquals(objectMapper.readTree(responseRealString), objectMapper.readTree(responseString));
    }

}

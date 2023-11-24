package lv.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.core.file.manager.TestFileManager;
import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.rest.RestConfig;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumController;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TravelPremiumControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    DateService dateService;

    static final String testRestJSONFilePath = "rest/";
    static final String requestFileName = "/request.json";
    static final String responseFileName = "/response.json";
    private final TestFileManager fileManager = new TestFileManager();

    private MockMvc mockMvc;

    /*@Test
    public void allOkFromFile() throws Exception {
        when(dateService.getTodayDate()).thenReturn(new Date(10L));
        when(dateService.createDate("from", ArgumentMatchers.any())).thenReturn(new Date(100L));
        when(dateService.createDate("to", ArgumentMatchers.any())).thenReturn(new Date(200L));
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
    }*/

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

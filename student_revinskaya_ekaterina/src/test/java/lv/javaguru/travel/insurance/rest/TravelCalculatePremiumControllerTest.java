package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRequestWithoutErrors() throws Exception {
        equalsJsonFiles("ControllerRequestWithoutErrors.json", "ControllerResponseWithoutErrors.json");
    }
    @Test
    public void testRequestWithoutFirstName() throws Exception {
        equalsJsonFiles("ControllerRequestWithoutFirstName.json", "ControllerResponseWithoutFirstName.json");
    }
    @Test
    public void testRequestWithoutLastName() throws Exception {
        equalsJsonFiles("ControllerRequestWithoutLastName.json", "ControllerResponseWithoutLastName.json");
    }
    @Test
    public void testRequestWithoutDateFrom() throws Exception {
        equalsJsonFiles("ControllerRequestWithoutDateFrom.json", "ControllerResponseWithoutDateFrom.json");
    }
    @Test
    public void testRequestWithoutDateTo() throws Exception {
        equalsJsonFiles("ControllerRequestWithoutDateTo.json", "ControllerResponseWithoutDateTo.json");
    }
    @Test
    public void testRequestWithNoFields() throws Exception {
        equalsJsonFiles("ControllerRequestWithNoFields.json", "ControllerResponseWithNoFields.json");
    }
    @Test
    public void testRequestWithDateFromMoreThanDateTo() throws Exception {
        equalsJsonFiles("ControllerRequestWithDateFromMoreThanTo.json", "ControllerResponseWithDateFromMoreThanTo.json");
    }
    public void equalsJsonFiles(String requestFile, String responseFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String response = mockMvc.perform(post("/insurance/travel/")
                        .content(parseJSONIntoString(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(mapper.readTree(response), mapper.readTree(parseJSONIntoString(responseFile)));
    }

    private String parseJSONIntoString(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}

package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTest() throws Exception {
        compareResponseFromRequest("requestCorrect.json", "responseCorrect.json");
        compareResponseFromRequest("requestWithoutFirstName.json", "responseWithoutFirstName.json");
        compareResponseFromRequest("requestWithoutLastName.json", "responseWithoutLastName.json");
        compareResponseFromRequest("requestWithoutDateFrom.json", "responseWithoutDateFrom.json");
        compareResponseFromRequest("requestWithoutDateTo.json", "responseWithoutDateTo.json");
        compareResponseFromRequest("requestWithDateFromMoreThanDateTo.json", "responseWithDateFromMoreThanDateTo.json");
        compareResponseFromRequest("requestEmpty.json", "responseEmpty.json");
        compareResponseFromRequest("requestDateFromInPast.json", "responseDateFromInPast.json");


    }

    private void compareResponseFromRequest(String pathToRequestJson,
                                            String pathToCorrectResponseJson) throws Exception {
        String jsonRequestAsString = parseData(pathToRequestJson);

        MvcResult resultOfResponse = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequestAsString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseAsString = resultOfResponse.getResponse().getContentAsString();
        String correctResponse = parseData(pathToCorrectResponseJson);

        JSONAssert.assertEquals(responseAsString, correctResponse, false);
    }

    public String parseData(String pathToFile) {
        try {
            File jsonFile = ResourceUtils.getFile("classpath:rest/" + pathToFile);
            return new String(Files.readAllBytes(jsonFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

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
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String response = mockMvc.perform(post("/insurance/travel/")
                        .content(parseJSONIntoString("TravelCalculatePremiumControllerRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andReturn().getResponse()
                        .getContentAsString();
        assertEquals(mapper.readTree(parseJSONIntoString("TravelCalculatePremiumControllerResponse.json")), mapper.readTree(response));
    }

    private String parseJSONIntoString(String fileName) throws IOException {
        try {
            File file = ResourceUtils.getFile("classpath:" + fileName);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new IOException("JSON file " + fileName + " can't be read");
        }
    }
}
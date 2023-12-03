package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader JsonReader;
    private ObjectMapper OMapper = new ObjectMapper();

    @Test
    public void testFirst() throws Exception
    {
        compare("rest/request.json",
                "rest/response.json");
    }
    @Test
    public void testSecond() throws Exception
    {
        compare("rest/request2.json",
                "rest/response2.json");
    }

    private void compare(String RequestFilePath,
                         String ResponseFilePath) throws Exception {
        String RequestJson = JsonReader.ReadJsonFileInString(RequestFilePath);
        String ResponseJson = JsonReader.ReadJsonFileInString(ResponseFilePath);
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                .content(RequestJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(OMapper.readTree(ResponseJson), OMapper.readTree(result.getResponse().getContentAsString()));
    }
}

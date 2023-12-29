package lv.javaguru.travel.insurance.rest.v1;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/insurance/travel/api/v1/";
    protected abstract String getTestCaseName();

    protected abstract boolean uuidIsPresent();
    @Test
    public void testRequest() throws Exception {
        equalsJsonFiles("rest/v1/" +getTestCaseName() + "/request.json", "rest/v1/" +getTestCaseName() + "/response.json");
    }

    public void equalsJsonFiles(String requestFile, String responseFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String response = mockMvc.perform(post(BASE_URL)
                        .content(parseJSONIntoString(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        if (uuidIsPresent()) {
            assertJson(mapper.readTree(response))
                    .where()
                    .path("uuid").matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .isEqualTo(parseJSONIntoString(responseFile));
        } else {
            assertJson(mapper.readTree(response))
                    .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .isEqualTo(parseJSONIntoString(responseFile));
        }
    }

    private String parseJSONIntoString(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

}

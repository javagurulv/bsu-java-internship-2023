package lv.javaguru.travel.insurance.core.rest.v2;

import lv.javaguru.travel.insurance.core.rest.common.JsonFileReader;
import org.json.JSONObject;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;
    private static final String BASE_URL = "/insurance/travel/api/v2/";
    protected abstract String getTestCaseFolderName();

   protected void executeAndCompare () throws Exception {
       executeAndCompare(
               "rest/v2/" + getTestCaseFolderName() + "/request.json",
               "rest/v2/" + getTestCaseFolderName() + "/response.json"
       );
   }


    protected void executeAndCompare(String jsonRequestFilePath,
                                       String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post(BASE_URL)
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);
        JSONAssert.assertEquals(responseBodyContent, jsonResponse,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("uuid", (o1,o2) -> true)));

        JSONObject jsonObject = new JSONObject(responseBodyContent);
        if (jsonObject.has("uuid")) {
            String generatedUUID = (String) jsonObject.get("uuid");
            assertThat(UUID.fromString(generatedUUID).toString()).isEqualTo(generatedUUID);
        }
       /* assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);*/
    }

}
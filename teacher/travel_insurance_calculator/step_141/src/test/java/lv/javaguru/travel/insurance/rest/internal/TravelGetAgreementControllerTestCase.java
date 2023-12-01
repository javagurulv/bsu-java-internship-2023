package lv.javaguru.travel.insurance.rest.internal;

import lv.javaguru.travel.insurance.rest.common.JsonFileReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelGetAgreementControllerTestCase {

    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    private static final String BASE_URL = "/insurance/travel/api/internal/agreement/";


    protected abstract String getTestCaseFolderName();

    protected void executeAndCompare(String agreementUuid) throws Exception {
        executeAndCompare(
                agreementUuid,
                "rest/" + getTestCaseFolderName() + "/get_agreement_response.json",
                false
        );
    }

    protected void executeAndCompare(String agreementUuid, boolean ignoreUUIDValue) throws Exception {
        executeAndCompare(
                agreementUuid,
                "rest/" + getTestCaseFolderName() + "/get_agreement_response.json",
                ignoreUUIDValue
        );
    }

    protected void executeAndCompare(String agreementUuid,
                                     String jsonResponseFilePath,
                                     boolean ignoreUUIDValue) throws Exception {
        MvcResult result = mockMvc.perform(get("/insurance/travel/api/internal/agreement/" + agreementUuid)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        if (ignoreUUIDValue) {
            assertJson(responseBodyContent)
                    .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .at("/uuid").isNotEmpty()
                    .isEqualTo(jsonResponse);
        } else {
            assertJson(responseBodyContent)
                    .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .isEqualTo(jsonResponse);
        }
    }

}

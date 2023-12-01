package lv.javaguru.travel.insurance.rest.internal;

import lv.javaguru.travel.insurance.rest.common.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelGetAgreementControllerTestCase2 extends TravelGetAgreementControllerTestCase {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    @Test
    public void shouldGetAgreement() throws Exception {
        executeAndCompare("UNKNOWN_UUID", true);
    }

    @Override
    protected String getTestCaseFolderName() {
        return "internal/test_case_2";
    }

}

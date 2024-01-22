package lv.javaguru.travel.insurance.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.utils.JsonReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonReaderTest {

    @Test
    void convertObjectToJsonTestWithEmptyRequest() throws JsonProcessingException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        String json = JsonReader.convertObjectToJson(request);

        assertTrue(json.equals("{\"personFirstName\":null,\"personLastName\":null,\"agreementDateFrom\":null,\"agreementDateTo\":null}"));

    }

    @Test
    void convertObjectToJsonTest() throws JsonProcessingException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("A");
        request.setPersonLastName("B");

        String json = JsonReader.convertObjectToJson(request);

        assertTrue(json.equals("{\"personFirstName\":\"A\",\"personLastName\":\"B\",\"agreementDateFrom\":null,\"agreementDateTo\":null}"));

    }
}

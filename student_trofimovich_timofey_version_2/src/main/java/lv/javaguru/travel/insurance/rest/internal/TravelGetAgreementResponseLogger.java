package lv.javaguru.travel.insurance.rest.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementResponseLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelGetAgreementResponseLogger.class);

    void log(TravelGetAgreementResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(response);
            LOGGER.info("RESPONSE: " + jsonString);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error to convert response to JSON", e);
        }
    }
}

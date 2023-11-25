package lv.javaguru.travel.insurance.rest.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void logResponse(TravelCalculatePremiumResponse response) {
        try {
            String json = objectMapper.writeValueAsString(response);
            logger.info("RESPONSE: {}", json);
        } catch (JsonProcessingException e) {
            logger.error("Error converting response to JSON", e);
        }
    }
}
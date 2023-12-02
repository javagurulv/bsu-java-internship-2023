package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumDatabaseLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumDatabaseLogger.class);

    public void log(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(message);
            logger.info("DATABASE: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert database message to JSON", e);
        }
    }
}

package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void log(TravelCalculatePremiumRequest request) {
        try {
            logger.info("REQUEST: " + objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            logger.error("Cannot convert request to json", e);
        }
    }
}

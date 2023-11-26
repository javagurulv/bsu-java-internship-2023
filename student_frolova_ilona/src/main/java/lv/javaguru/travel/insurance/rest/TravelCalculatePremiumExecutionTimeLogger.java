package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumExecutionTimeLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumExecutionTimeLogger.class);

    public void log(long executionInMilliseconds) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(executionInMilliseconds);
            logger.info("EXECUTION TIME: " + json + " milliseconds");
        } catch (JsonProcessingException e) {
            logger.error("Error to convert execution time to JSON", e);
        }
    }
}

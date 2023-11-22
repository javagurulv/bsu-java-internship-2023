package lv.javaguru.travel.insurance.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {
    public static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    public void log(TravelCalculatePremiumRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + jsonString);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}

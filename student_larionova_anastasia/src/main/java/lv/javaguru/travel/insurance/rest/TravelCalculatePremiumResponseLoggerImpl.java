package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLoggerImpl implements TravelCalculatePremiumResponseLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerImpl.class);

    @Override
    public void logResponse(TravelCalculatePremiumResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            logger.info("RESRONSE: {}", json);
        } catch (JsonProcessingException e) {
            logger.error("Error converting request to JSON", e);
        }
    }

}

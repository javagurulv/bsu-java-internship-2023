package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    void log(TravelCalculatePremiumResponse response){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String jsonResponseToString = mapper.writeValueAsString(response);
            LOGGER.info("RESPONSE: " + jsonResponseToString);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error to convert response to JSON", e);
        }
    }
}

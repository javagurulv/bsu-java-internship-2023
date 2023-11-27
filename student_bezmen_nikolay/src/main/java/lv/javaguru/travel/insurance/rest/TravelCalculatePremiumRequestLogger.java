package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    void log(TravelCalculatePremiumRequest request){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String jsonRequestToString = mapper.writeValueAsString(request);
            LOGGER.info("REQUEST: " + jsonRequestToString);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error to convert request to JSON", e);
        }
    }
}

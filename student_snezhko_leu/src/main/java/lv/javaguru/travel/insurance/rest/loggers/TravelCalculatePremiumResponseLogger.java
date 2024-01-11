package lv.javaguru.travel.insurance.rest.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);
    public void log(TravelCalculatePremiumResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            String jsonForm = mapper.writeValueAsString(response);
            logger.info(jsonForm);
        }
        catch (JsonProcessingException e) {
            logger.error("Error to convert response to JSON");
        }
    }
}

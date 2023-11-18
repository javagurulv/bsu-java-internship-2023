package lv.javaguru.travel.insurance.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);
    public void log(TravelCalculatePremiumResponse response){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(response);
            logger.info("RESPONSE:\n" + jsonString);
        }catch(JsonProcessingException ex){
            logger.error(ex.getMessage());
        }

    }
}

package lv.javaguru.travel.insurance.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TravelCalculatePremiumRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    public void log(TravelCalculatePremiumRequest request){
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonString = mapper.writeValueAsString(request);
                logger.info("REQUEST:\n" + jsonString);
            }catch(JsonProcessingException ex){
logger.error(ex.getMessage());
        }

    }
}

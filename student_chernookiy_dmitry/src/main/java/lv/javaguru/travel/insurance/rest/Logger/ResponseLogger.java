package lv.javaguru.travel.insurance.rest.Logger;


import com.fasterxml.jackson.core.JsonProcessingException;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.utils.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResponseLogger {

    private static final Logger logger = LoggerFactory.getLogger(ResponseLogger.class);

    public void log(TravelCalculatePremiumResponse response) {
        try {
            logger.info("RESPONSE: " + JsonReader.convertObjectToJson(response));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }


}
package lv.javaguru.travel.insurance.rest.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.utils.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class RequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    public void log (TravelCalculatePremiumRequest request) {
        try {
            logger.info("REQUEST: " + JsonReader.convertObjectToJson(request));
        } catch (JsonProcessingException exception) {
            logger.error(exception.getMessage());
        }

    }
}
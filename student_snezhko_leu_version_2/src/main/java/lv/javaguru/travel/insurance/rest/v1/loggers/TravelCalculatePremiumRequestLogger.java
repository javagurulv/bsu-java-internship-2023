package lv.javaguru.travel.insurance.rest.v1.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.api.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    public void log(TravelCalculatePremiumRequestV1 request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonFormat = mapper.writeValueAsString(request);
            logger.info(jsonFormat);
        }
        catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON");
        }
    }
}

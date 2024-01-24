package lv.javaguru.travel.insurance.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLoggerImpl implements TravelCalculatePremiumRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerImpl.class);

    @Override
    public void logRequest(TravelCalculatePremiumRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: {}", json);
        } catch (JsonProcessingException e) {
            logger.error("Error converting request to JSON", e);
        }
    }
}

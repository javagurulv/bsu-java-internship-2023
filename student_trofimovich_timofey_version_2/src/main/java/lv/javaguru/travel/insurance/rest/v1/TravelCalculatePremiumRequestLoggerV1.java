package lv.javaguru.travel.insurance.rest.v1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLoggerV1 {
    public static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV1.class);

    public void log(TravelCalculatePremiumRequestV1 request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + jsonString);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}

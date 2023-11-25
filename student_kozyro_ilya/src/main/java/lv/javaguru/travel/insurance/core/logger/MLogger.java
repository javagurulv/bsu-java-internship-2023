package lv.javaguru.travel.insurance.core.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MLogger {

    public void logTravelPremiumRequest(TravelCalculatePremiumRequest request) {
        logObject(request);
    }

    private void logObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(object);
            log.info(json);
        } catch (JsonProcessingException e) {
            log.error("Error while converting object to JSON");
        }
    }
}

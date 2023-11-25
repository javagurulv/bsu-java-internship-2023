package lv.javaguru.travel.insurance.logger;

import lombok.extern.slf4j.Slf4j;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
@Slf4j
@Component
public
class TravelCalculatePremiumRequestLogger {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void log(TravelCalculatePremiumRequest request) {
        try {
            String json = objectMapper.writeValueAsString(request);
            log.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert request to JSON", e);
        }
    }
}


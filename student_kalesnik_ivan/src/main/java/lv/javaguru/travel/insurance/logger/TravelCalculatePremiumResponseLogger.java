package lv.javaguru.travel.insurance.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumResponse;

@Slf4j
@Component
public class TravelCalculatePremiumResponseLogger {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void log(TravelCalculatePremiumResponse response) {
        try {
            String json = objectMapper.writeValueAsString(response);
            log.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert response to JSON", e);
        }
    }
}


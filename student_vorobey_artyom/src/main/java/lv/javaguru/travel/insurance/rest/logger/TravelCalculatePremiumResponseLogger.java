package lv.javaguru.travel.insurance.rest.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumResponseLogger {
    public void logResponse(TravelCalculatePremiumResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String JsonResponse = objectMapper.writeValueAsString(response);
            log.info("RESPONSE: " + JsonResponse);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert RESPONSE to Json" + e);
        }
    }
}

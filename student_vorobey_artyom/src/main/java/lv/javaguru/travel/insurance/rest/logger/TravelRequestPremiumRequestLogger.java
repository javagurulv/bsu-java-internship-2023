package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TravelRequestPremiumRequestLogger {
    public void logRequest(TravelCalculatePremiumRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            log.info("REQUEST: " + jsonRequest);
        } catch(JsonProcessingException e) {
            log.error("Failed to convert request to Json" + e);
        }
    }
}

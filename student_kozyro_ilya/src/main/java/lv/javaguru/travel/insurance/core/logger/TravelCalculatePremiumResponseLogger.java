package lv.javaguru.travel.insurance.core.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumResponseLogger extends MLogger{
    public void logResponse(TravelCalculatePremiumResponse response) {
        try {
            log.info("Response: ".concat(logObject(response)));
        } catch (JsonProcessingException e) {
            log.error("Error due parsing");
        }
    }
}

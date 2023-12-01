package lv.javaguru.travel.insurance.core.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumRequestLogger extends MLogger{
    public void logRequest(TravelCalculatePremiumRequest request) {
        try {
            log.info("Request: ".concat(logObject(request)));
        } catch (JsonProcessingException e) {
            log.error("Error due parsing");
        }
    }
}

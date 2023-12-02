package lv.javaguru.travel.insurance.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLoggerV2 {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV2.class);

    public void log(TravelCalculatePremiumRequestV2 request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(request);
            logger.info("REQUEST:\n" + jsonString);
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }

    }
}

package lv.javaguru.travel.insurance.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {
    private final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequest.class);

    void toLog(TravelCalculatePremiumResponse response){
        ObjectMapper mapper = new ObjectMapper();

        try {
            String responseAsJson = mapper.writeValueAsString(response);
            logger.info("Response: " + responseAsJson);
        }catch (JsonProcessingException exception){
            logger.error("Problem with converting response to JSON!", exception);
        }
    }
}

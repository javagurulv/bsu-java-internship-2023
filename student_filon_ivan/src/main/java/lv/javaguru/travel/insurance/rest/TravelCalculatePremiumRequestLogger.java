package lv.javaguru.travel.insurance.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class TravelCalculatePremiumRequestLogger {
    private final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequest.class);

    void toLog(TravelCalculatePremiumRequest request){
        ObjectMapper mapper = new ObjectMapper();

        try{
            String requestAsJson = mapper.writeValueAsString(request);
            logger.info("Request: " + requestAsJson);
        }catch(JsonProcessingException exception){
            logger.error("Problems with converting request to JSON!", exception);
        }
    }
}

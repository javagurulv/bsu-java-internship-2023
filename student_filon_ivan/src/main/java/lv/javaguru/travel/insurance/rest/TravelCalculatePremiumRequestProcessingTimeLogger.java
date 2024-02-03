package lv.javaguru.travel.insurance.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class TravelCalculatePremiumRequestProcessingTimeLogger {
    private final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestProcessingTimeLogger.class);

    void toLog(StopWatch stopWatch){
        stopWatch.stop();
        long ms = stopWatch.getLastTaskTimeMillis();
        logger.info("Request processing time (ms): " + ms);
    }
}

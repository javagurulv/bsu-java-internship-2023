package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestProcessingTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestProcessingTimeLogger.class);

    public void log(Stopwatch stopWatch) {
        stopWatch.stop();
        logger.info("Request processing time (ms): " + stopWatch.elapsed().toMillis());
    }
}

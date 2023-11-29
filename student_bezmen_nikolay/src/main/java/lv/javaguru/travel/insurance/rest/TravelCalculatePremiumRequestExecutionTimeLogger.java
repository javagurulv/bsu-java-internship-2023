package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    void log(Stopwatch watch){
        watch.stop();
        long timeProcess = watch.elapsed().toMillis();
        LOGGER.info("Request processing time (ms): " + timeProcess);

    }
}

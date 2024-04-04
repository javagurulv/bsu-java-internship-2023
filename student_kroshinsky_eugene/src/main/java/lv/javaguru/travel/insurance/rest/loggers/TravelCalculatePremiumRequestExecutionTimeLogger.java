package lv.javaguru.travel.insurance.rest.loggers;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);
    public void logRequest(Stopwatch stopwatch) {
            long result = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            logger.info("Request processing time (ms): {}", result);
    }
}

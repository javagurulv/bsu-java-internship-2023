package lv.javaguru.travel.insurance.rest.logger;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);
    public void logRequestExecTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsedMilliseconds = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): " + Long.toString(elapsedMilliseconds));
    }
}

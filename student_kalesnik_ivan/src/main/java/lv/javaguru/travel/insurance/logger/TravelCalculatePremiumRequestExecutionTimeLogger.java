package lv.javaguru.travel.insurance.logger;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    public void logExecutionTime(Stopwatch stopwatch) {
        // Ensure the stopwatch is stopped
        if (!stopwatch.isRunning()) {
            stopwatch.stop();
        }
        long elapsedMillis = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): " + elapsedMillis);
    }
}

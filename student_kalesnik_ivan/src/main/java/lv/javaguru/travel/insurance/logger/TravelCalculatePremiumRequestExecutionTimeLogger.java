package lv.javaguru.travel.insurance.logger;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    public TravelCalculatePremiumRequestExecutionTimeLogger(Logger logger) {
        this.logger = logger;
    }
    public void logExecutionTime(Runnable task) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            task.run();
        } finally {
            stopwatch.stop();
            long timeElapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            logger.info("Request processing time (ms): " + timeElapsed);
        }
    }
}

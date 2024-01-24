package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(RequestExecutionTimeLogger.class);

    void log(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): {}", elapsedMillis);
    }
}

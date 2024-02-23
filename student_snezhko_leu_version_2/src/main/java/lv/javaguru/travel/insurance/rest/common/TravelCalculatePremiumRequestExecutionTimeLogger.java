package lv.javaguru.travel.insurance.rest.common;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);
    public void log(Stopwatch stopWatch) {
        if (!stopWatch.isRunning()) {
            logger.info("Request processing time (ms): " + stopWatch.elapsed(TimeUnit.MILLISECONDS));
        }
        else {
            logger.error("Request processing is not finished");
        }
    }
}

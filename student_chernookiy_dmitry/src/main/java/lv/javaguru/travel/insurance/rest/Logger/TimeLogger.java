package lv.javaguru.travel.insurance.rest.Logger;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TimeLogger.class);

    public void log(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        logger.info("TIME: " + elapsedMillis);
    }
}

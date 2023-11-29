package lv.javaguru.travel.insurance.rest.logger;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    public void logRequestExecTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsedMilliseconds = stopwatch.elapsed().toMillis();
        log.info("Request processing time (ms): " + Long.toString(elapsedMilliseconds));
    }
}

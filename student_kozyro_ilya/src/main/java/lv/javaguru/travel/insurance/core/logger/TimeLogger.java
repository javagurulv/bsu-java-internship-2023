package lv.javaguru.travel.insurance.core.logger;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TimeLogger extends MLogger{

    public void logElapsedTime(Stopwatch stopwatch) {

        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        String toLog = "Request processing time (ms): " + elapsedMillis;
        if (elapsedMillis > 200) {
            log.warn(toLog);
        } else {
            log.info(toLog);
        }
    }
}

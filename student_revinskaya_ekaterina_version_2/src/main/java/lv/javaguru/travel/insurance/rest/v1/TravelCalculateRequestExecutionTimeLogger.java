package lv.javaguru.travel.insurance.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//2023-03-13 08:15:44,682 [http-nio-8080-exec-1]
// INFO  l.j.j.i.r.TravelCalculatePremiumRequestExecutionTimeLogger - Request processing time (ms): 70
@Component
public class TravelCalculateRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculateRequestExecutionTimeLogger.class);

    public void log(long millis) {
        logger.info("Request processing time (ms): " + millis);
    }
}

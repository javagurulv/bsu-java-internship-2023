package lv.javaguru.travel.insurance.logger;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TimingTravelCalculatePremiumRequestLogger implements TravelCalculatePremiumRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TimingTravelCalculatePremiumRequestLogger.class);

    @Qualifier("travelCalculatePremiumRequestLoggerImpl")
    TravelCalculatePremiumRequestLogger delegateLogger;

    public TimingTravelCalculatePremiumRequestLogger(@Qualifier("travelCalculatePremiumRequestLoggerImpl") TravelCalculatePremiumRequestLogger delegateLogger) {
        this.delegateLogger = delegateLogger;
    }

    @Override
    public void logRequest(TravelCalculatePremiumRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        delegateLogger.logRequest(request);

        stopwatch.stop();
        long elapsedTime = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): {}", elapsedTime);
    }
}

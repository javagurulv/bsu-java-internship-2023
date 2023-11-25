package lv.javaguru.travel.insurance.core.loggerTest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TravelCalculatePremiumRequestExecutionTimeLoggerTest {
    @Test
    public void testLogExecutionTime() {
        TravelCalculatePremiumRequestExecutionTimeLogger logger = new TravelCalculatePremiumRequestExecutionTimeLogger();
        Stopwatch stopwatch = Stopwatch.createStarted();
        logger.logExecutionTime(stopwatch);
        assertTrue(stopwatch.isRunning());
    }

    @Test
    public void testLogExecutionTimeWhenStopped() {
        TravelCalculatePremiumRequestExecutionTimeLogger logger = new TravelCalculatePremiumRequestExecutionTimeLogger();
        Stopwatch stopwatch = Stopwatch.createStarted();
        logger.logExecutionTime(stopwatch);
        assertTrue(stopwatch.isRunning());
    }
}

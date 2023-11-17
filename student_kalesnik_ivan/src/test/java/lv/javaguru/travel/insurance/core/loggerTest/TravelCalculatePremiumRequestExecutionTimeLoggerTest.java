package lv.javaguru.travel.insurance.core.loggerTest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestExecutionTimeLoggerTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;

    @Test
    public void logExecutionTimeTest() {
        Logger logger = Mockito.mock(Logger.class);
        Runnable task = Mockito.mock(Runnable.class);
        TravelCalculatePremiumRequestExecutionTimeLogger loggerUnderTest =
                new TravelCalculatePremiumRequestExecutionTimeLogger(logger);
        loggerUnderTest.logExecutionTime(task);
        verify(task, times(1)).run();
        verify(logger, times(1)).info(startsWith("Request processing time (ms): "));
    }
}


package lv.javaguru.travel.insurance.core.loggerTest;

import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestLoggerTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private TravelCalculatePremiumRequestLogger travelCalculatePremiumRequestLogger;

    @Test
    void log_emptyRequest() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequestLogger.log(request);
        verify(logger, never()).info(anyString());
    }

}

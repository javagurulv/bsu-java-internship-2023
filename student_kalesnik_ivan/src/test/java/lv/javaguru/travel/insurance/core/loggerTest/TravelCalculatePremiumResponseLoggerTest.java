package lv.javaguru.travel.insurance.core.loggerTest;

import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumResponseLogger;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumResponseLogger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumResponseLoggerTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private TravelCalculatePremiumResponseLogger travelCalculatePremiumResponseLogger;


    @Test
    void log_emptyRequest() {
        TravelCalculatePremiumResponse request = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponseLogger.log(request);
        verify(logger, never()).info(anyString());
    }
}

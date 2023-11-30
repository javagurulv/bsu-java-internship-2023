package lv.javaguru.travel.insurance.core.loggerTest;

import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

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
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        travelCalculatePremiumRequestLogger.log(request);
        verify(logger, never()).info(anyString());
    }

}

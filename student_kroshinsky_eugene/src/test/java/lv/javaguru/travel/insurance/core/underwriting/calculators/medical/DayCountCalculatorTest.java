package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private DateTimeUtil dateTimeUtil;
    @InjectMocks private DayCountCalculator dayCountCalculator;
    @Test
    void injectedObjectsAreNotNull() {
        assertNotNull(dateTimeUtil);
        assertNotNull(request);
        assertNotNull(dayCountCalculator);
    }
    @Test
    void calculateTest() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.11.2025"));
        when(request.getAgreementDateFrom()).thenReturn(createDate("24.11.2025"));
        when(dateTimeUtil.calculateDateDifference(any(), any())).thenReturn(BigDecimal.valueOf(12));

        BigDecimal result = dayCountCalculator.calculate(request);
        assertEquals(BigDecimal.valueOf(12), result);

    }
    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}
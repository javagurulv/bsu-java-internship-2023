package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private DayCountCalculator calculator;

    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(Date.from(LocalDate.of(2023, 4, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setAgreementDateTo(Date.from(LocalDate.of(2023, 4, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    void shouldCalculateDayCountCorrectly() {
        long expectedDays = 10;
        when(dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(expectedDays);
        BigDecimal result = calculator.calculate(request);
        assertEquals(BigDecimal.valueOf(expectedDays), result);
    }

}
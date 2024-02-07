package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
public class DayCountCalculatorTest {
    @Mock DateTimeUtil dateTimeUtil;

    @InjectMocks DayCountCalculator calculator;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(Date.from(LocalDate.of(2033, 4, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setAgreementDateTo(Date.from(LocalDate.of(2033, 4, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    public void shouldCalculateDaysCountCorrectly() {
        long expectedDays = 10;
        when(dateTimeUtil.getDifferenceInDays(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(expectedDays);
        BigDecimal result = calculator.calculate(request);
        assertEquals(BigDecimal.valueOf(expectedDays), result);
    }
}

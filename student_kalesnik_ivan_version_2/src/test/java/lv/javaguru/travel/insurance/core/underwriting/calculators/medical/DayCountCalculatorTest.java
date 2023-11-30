package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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

    private AgreementDTO agreement;

    @BeforeEach
    void setUp() {
        agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(Date.from(LocalDate.of(2023, 4, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        agreement.setAgreementDateTo(Date.from(LocalDate.of(2023, 4, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    void shouldCalculateDayCountCorrectly() {
        long expectedDays = 10;
        when(dateTimeUtil.getDaysBetween(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo())).thenReturn(expectedDays);
        BigDecimal result = calculator.calculate(agreement);
        assertEquals(BigDecimal.valueOf(expectedDays), result);
    }

}
package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DayCountCalculatorTest {
    @InjectMocks private DayCountCalculator dayCountCalculator;
    @Mock private AgreementDTO agreementDTO;
    @Mock private DateTimeUtil dateTimeUtil;
    @Test
    public void calculateDayCountTest(){
        Date date1 =createDate("03.04.2023");
        Date date2 =createDate("09.04.2023");
        when(agreementDTO.getAgreementDateFrom()).thenReturn(date1);
        when(agreementDTO.getAgreementDateTo()).thenReturn(date2);
        when(dateTimeUtil.calculateDiffBetweenDays(date1,date2)).thenReturn(BigDecimal.valueOf(6));
        assertEquals(dayCountCalculator.calculate(agreementDTO), BigDecimal.valueOf(6));
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

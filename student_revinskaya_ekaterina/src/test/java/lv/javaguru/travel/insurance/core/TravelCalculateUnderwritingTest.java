package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculateUnderwritingTest {
    @Mock
    private CalculatorDate calculatorDate;
    @InjectMocks
    private TravelCalculateUnderwriting calculateUnderwriting;

    @Test
    public void rightCalculateUnderwriting(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("03.07.2022"));
        when(request.getAgreementDateTo()).thenReturn(createDate("07.07.2023"));
        when(calculatorDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(BigDecimal.valueOf(4));
        assertEquals(calculateUnderwriting.calculateAgreementPrice(request), BigDecimal.valueOf(4));
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

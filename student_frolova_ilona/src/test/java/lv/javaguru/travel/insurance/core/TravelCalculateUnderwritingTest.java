package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculateUnderwritingTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks private TravelCalculateUnderwriting underwritingCalculator;

    TravelCalculateUnderwritingTest() {
        underwritingCalculator = new TravelCalculateUnderwriting();
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void costIsZeroIfDaysAreEqual() {

        when(dateTimeService.createDate(any())).thenReturn(new Date(86400000L));
        Date date = dateTimeService.createDate("");

        when(request.getAgreementDateFrom()).thenReturn(date);
        when(request.getAgreementDateTo()).thenReturn(date);

        BigDecimal cost = underwritingCalculator.calculatePremium(request);

        assertEquals(cost.compareTo(BigDecimal.ZERO), 0);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void costIsThree() {

        when(dateTimeService.createDate(any())).thenReturn(new Date(86400000L));
        Date date1 = dateTimeService.createDate("");

        when(dateTimeService.createDate(any())).thenReturn(new Date(345600000L));
        Date date2 = dateTimeService.createDate("");

        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);

        BigDecimal cost = underwritingCalculator.calculatePremium(request);

        assertEquals(cost.compareTo(BigDecimal.valueOf(3)), 0);
    }
}

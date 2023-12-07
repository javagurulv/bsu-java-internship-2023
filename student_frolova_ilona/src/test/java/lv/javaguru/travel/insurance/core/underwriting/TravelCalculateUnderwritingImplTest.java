package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.TravelCalculateUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculateUnderwritingImpl;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TravelCalculateUnderwritingImplTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelCalculateUnderwritingImpl underwritingCalculator;

    @BeforeEach
    public void defineMocksBehavior() {
        when(dateTimeUtil.getDifferenceInDays(any(), any())).thenReturn(BigDecimal.ZERO);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void costIsZeroIfDaysAreEqual() {

        when(dateTimeUtil.createDate(any())).thenReturn(new Date(86400000L));
        Date date = dateTimeUtil.createDate("");

        when(request.getAgreementDateFrom()).thenReturn(date);
        when(request.getAgreementDateTo()).thenReturn(date);

        BigDecimal cost = underwritingCalculator.calculatePremium(request);

        assertEquals(cost.compareTo(BigDecimal.ZERO), 0);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void costIsThree() {

        Date date1 = new Date(86400000L);
        Date date2 = new Date(345600000L);

        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);
        when(dateTimeUtil.getDifferenceInDays(date1, date2)).thenReturn(BigDecimal.valueOf(3));

        BigDecimal cost = underwritingCalculator.calculatePremium(request);

        assertEquals(0, cost.compareTo(BigDecimal.valueOf(3)));
    }
}

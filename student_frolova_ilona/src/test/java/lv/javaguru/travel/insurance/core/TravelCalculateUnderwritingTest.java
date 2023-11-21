package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TravelCalculateUnderwritingTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculateUnderwriting underwritingCalculator;

    @BeforeEach
    public void defineMocksBehavior() {
        when(dateTimeService.getDifferenceInDays(any(), any())).thenReturn(BigDecimal.ZERO);
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

        Date date1 = new Date(86400000L);
        Date date2 = new Date(345600000L);

        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);
        when(dateTimeService.getDifferenceInDays(date1, date2)).thenReturn(BigDecimal.valueOf(3));

        BigDecimal cost = underwritingCalculator.calculatePremium(request);

        assertEquals(0, cost.compareTo(BigDecimal.valueOf(3)));
    }
}

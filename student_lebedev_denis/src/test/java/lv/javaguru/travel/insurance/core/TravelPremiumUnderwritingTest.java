package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    private final DateTimeService dateTimeService = new DateTimeService();

    @Mock
    private DateTimeService dateTimeServiceMock;

    @InjectMocks
    private TravelPremiumUnderwriting underwritingMock;

    @Test
    public void shouldReturnCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(dateTimeService.createDate("02.03.2020"));
        when(request.getAgreementDateTo()).thenReturn(dateTimeService.createDate("22.03.2020"));
        when(dateTimeServiceMock.getDays(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(20L);
        assertEquals(underwritingMock.calculatePremium(request), new BigDecimal(20));
    }
}

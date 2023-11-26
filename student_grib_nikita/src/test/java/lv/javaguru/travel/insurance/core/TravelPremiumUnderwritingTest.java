package lv.javaguru.travel.insurance.core;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    @Mock private DataTimeService dateTimeService;
    @Mock TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void agreementPriceShouldEqualsSix(){
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 12, 8));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(2023, 12, 14));
        Mockito.when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(),
                                                    request.getAgreementDateTo())).thenReturn(6L);

        BigDecimal price = premiumUnderwriting.calculatePremium(request);
        assertEquals(price, new BigDecimal(6));
    }

    @Test
    public void agreementPriceShouldEqualsZero(){
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 12, 8));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(2023, 12, 8));
        Mockito.when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(),
                request.getAgreementDateTo())).thenReturn(0L);

        BigDecimal price = premiumUnderwriting.calculatePremium(request);
        assertEquals(price, new BigDecimal(0));
    }
}

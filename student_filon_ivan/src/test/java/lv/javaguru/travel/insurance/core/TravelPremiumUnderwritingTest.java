package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @Mock private DateTimeService service;
    @Mock TravelCalculatePremiumRequest request;
    @InjectMocks TravelPremiumUnderwriting underwriting;

    @Test
    public void correctAgreementPrice() throws ParseException {
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new SimpleDateFormat("dd.MM.yyyy").parse("21.05.2000"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new SimpleDateFormat("dd.MM.yyyy").parse("12.05.2000"));
        Mockito.when(service.getDaysBetween(request.getAgreementDateTo(), request.getAgreementDateFrom())).thenReturn(9L);
        BigDecimal premium = underwriting.calculatePremium(request);
        assertEquals(premium, new BigDecimal(9));
    }
}

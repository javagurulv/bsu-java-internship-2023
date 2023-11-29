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
public class TravelPremiumUnderwritingTest {

    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(9L);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertEquals(premium, new BigDecimal(9));
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
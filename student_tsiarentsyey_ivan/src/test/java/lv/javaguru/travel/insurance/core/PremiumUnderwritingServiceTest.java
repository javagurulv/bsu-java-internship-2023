package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class PremiumUnderwritingServiceTest {

    @Autowired
    PremiumUnderwritingService underwritingService;

    @Test
    void shouldReturnZeroWhenZeroDiff() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(createDate("01.11.2020"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(createDate("01.11.2020"));
        assertThat(underwritingService.calculatePremium(request)).isEqualTo(new BigDecimal(0));
    }

    @Test
    void shouldReturnPositiveWhenPositiveDiff() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(createDate("01.11.2020"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(createDate("11.11.2020"));
        assertThat(underwritingService.calculatePremium(request)).isEqualTo(new BigDecimal(10));
    }

    private static Date createDate(String simpleDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(simpleDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

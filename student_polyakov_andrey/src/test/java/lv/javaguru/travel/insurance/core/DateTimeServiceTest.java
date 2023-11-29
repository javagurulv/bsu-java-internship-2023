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
public class DateTimeServiceTest {
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelUnderwritingProcess premiumUnderwriting;

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

   @Test
   public void shouldAgreementPriceBeCorrect() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("11.01.2023"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(10L);
        BigDecimal agreementPrice = premiumUnderwriting.calculatePremium(request);
        assertEquals(agreementPrice, new BigDecimal(10));
    }

    @Test
    public void shouldDaysBetweenBeZero() {
        DateTimeService dateTimeService = new DateTimeService();
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        DateTimeService dateTimeService = new DateTimeService();
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("05.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 4L);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        DateTimeService dateTimeService = new DateTimeService();
        Date date1 = createDate("05.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, -4L);
    }
}

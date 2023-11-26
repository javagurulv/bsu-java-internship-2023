package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    @Autowired
    private TravelCalculatePremiumServiceImpl service;
    private DateTimeService dateTimeService= new DateTimeService();


    @Test
    public void shouldReturnZeroAgreementPrice() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        var daysBetween = dateTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysBetween, 0);
    }

    @Test
    public void shouldReturnPositiveAgreementPrice() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        var daysBetween = dateTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysBetween, 365);
    }

    @Test
    public void shouldReturnNegativeAgreementPrice() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        var daysBetween = dateTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals( daysBetween, -365);
    }
}

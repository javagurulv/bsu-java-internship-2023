package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeServiceTest {

    private DateTimeService service;

    public DateTimeServiceTest() {
        service = new DateTimeService();
    }

    @Test
    public void differenceBetweenTwoSameDaysIsZero() {
        Date date1 = service.createDate("12.01.2005");
        Date date2 = service.createDate("12.01.2005");

        assertEquals(0, service.getDifferenceInDays(date1, date2).compareTo(BigDecimal.ZERO));
    }

    @Test
    public void OneDayDifference() {
        Date date1 = service.createDate("12.01.2005");
        Date date2 = service.createDate("13.01.2005");

        assertEquals(0, service.getDifferenceInDays(date1, date2).compareTo(BigDecimal.ONE));
    }

    @Test
    public void OneAndAHalfDayDifference() {
        Date date1 = new Date(12L);
        Date date2 = new Date(129600018L);

        BigDecimal result = service.getDifferenceInDays(date1, date2).setScale(5, RoundingMode.HALF_EVEN);
        assertEquals(result.compareTo(new BigDecimal("1.5")), 0);
    }
}

package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilTest {

    private final DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void differenceBetweenTwoSameDaysIsZero() {
        Date date1 = dateTimeUtil.createDate("12.01.2005");
        Date date2 = dateTimeUtil.createDate("12.01.2005");
        assertEquals(0L, dateTimeUtil.getDifferenceInDays(date1, date2));
    }

    @Test
    public void OneDayDifference() {
        Date date1 = dateTimeUtil.createDate("12.01.2005");
        Date date2 = dateTimeUtil.createDate("13.01.2005");

        assertEquals(1L, dateTimeUtil.getDifferenceInDays(date1, date2));
    }

    @Test
    public void TenDayDifference() {
        Date date1 = dateTimeUtil.createDate("12.01.2005");
        Date date2 = dateTimeUtil.createDate("22.01.2005");

        assertEquals(10L, dateTimeUtil.getDifferenceInDays(date1, date2));
    }

    @Test
    public void negativeDayDifference() {
        Date date1 = dateTimeUtil.createDate("22.01.2005");
        Date date2 = dateTimeUtil.createDate("12.01.2005");

        assertEquals(-10L, dateTimeUtil.getDifferenceInDays(date1, date2));
    }
}

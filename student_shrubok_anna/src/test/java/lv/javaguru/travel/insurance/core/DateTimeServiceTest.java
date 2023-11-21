package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    GregorianCalendar calendar = new GregorianCalendar();
    @Mock
    private DateTimeService dateTimeService;

    @BeforeEach
    void rewrite() { dateTimeService = new DateTimeService();}

    @Test
    void returnsCorrectResultWithPositiveDifference()
    {
        calendar.set(2005,Calendar.APRIL,14);
        Date date1 = calendar.getTime();
        calendar.set(2006,Calendar.APRIL,14);
        Date date2 = calendar.getTime();
        long days = dateTimeService.getDaysBetween(date2,date1);
        assertEquals(365, days);
    }

    @Test
    void returnsCorrectResultWithNegativeDifference()
    {
        calendar.set(2005,Calendar.APRIL,14);
        Date date1 = calendar.getTime();
        calendar.set(2006,Calendar.APRIL,14);
        Date date2 = calendar.getTime();
        long days = dateTimeService.getDaysBetween(date1,date2);
        assertEquals(-365, days);
    }

    @Test
    void returnsCorrectResultWithZeroDifference()
    {
        calendar.set(2005,Calendar.APRIL,14);
        Date date1 = calendar.getTime();
        Date date2 = calendar.getTime();
        long days = dateTimeService.getDaysBetween(date2,date1);
        assertEquals(0, days);
    }


}

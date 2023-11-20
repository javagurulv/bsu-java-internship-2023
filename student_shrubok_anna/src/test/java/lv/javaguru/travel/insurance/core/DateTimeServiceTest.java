package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    @Mock
    private DateTimeService dateTimeService;

    @BeforeEach
    void rewrite() { dateTimeService = new DateTimeService();}

    @Test
    void returnsCorrectResultWithPositiveDifference()
    {
        Date date1 = new Date(2005, Calendar.APRIL,14);
        Date date2 = new Date(2006, Calendar.APRIL,14);
        long days = dateTimeService.getDaysBetween(date2,date1);
        assertEquals(365, days);
    }

    @Test
    void returnsCorrectResultWithNegativeDifference()
    {
        Date date1 = new Date(2005, Calendar.APRIL,14);
        Date date2 = new Date(2006, Calendar.APRIL,14);
        long days = dateTimeService.getDaysBetween(date1,date2);
        assertEquals(365, days);
    }

    @Test
    void returnsCorrectResultWithZeroDifference()
    {
        Date date1 = new Date(2005, Calendar.APRIL,14);
        Date date2 = new Date(2005, Calendar.APRIL,14);
        long days = dateTimeService.getDaysBetween(date2,date1);
        assertEquals(0, days);
    }


}

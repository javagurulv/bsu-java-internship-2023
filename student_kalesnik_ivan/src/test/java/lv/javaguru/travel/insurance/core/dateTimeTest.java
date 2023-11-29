package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class dateTimeTest {

    @Mock
    private DateTimeUtil dateTimeService;

    @BeforeEach
    void setUp() {
        dateTimeService = new DateTimeUtil();
    }

    @Test
    void shouldReturnCorrectDaysBetweenPositive() {
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 5);
        Date date2 = calendar.getTime();

        long daysBetween = dateTimeService.getDaysBetween(date1, date2);

        assertEquals(5, daysBetween);
    }

    @Test
    void shouldReturnCorrectDaysBetweenNegative() {
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -5);
        Date date2 = calendar.getTime();

        long daysBetween = dateTimeService.getDaysBetween(date1, date2);

        assertEquals(-5, daysBetween);
    }

    @Test
    void shouldReturnCorrectDaysBetweenZero() {
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date date2 = calendar.getTime();

        long daysBetween = dateTimeService.getDaysBetween(date1, date2);

        assertEquals(0, daysBetween);
    }


}
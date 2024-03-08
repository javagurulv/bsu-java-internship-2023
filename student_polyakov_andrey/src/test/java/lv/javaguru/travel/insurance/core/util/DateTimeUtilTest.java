package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class DateTimeUtilTest {

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldDaysBetweenBeZero() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("05.01.2023");
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 4L);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        Date date1 = createDate("05.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(daysBetween, -4L);
    }
}

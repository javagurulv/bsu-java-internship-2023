package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {
    private final DateTimeService service = new DateTimeService();
    @Test
    public void shouldBeNoDays() throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005");
        Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005");
        assertEquals(service.getDaysBetween(date1,date), 0L);
    }

    @Test
    public void shouldBeNegative() throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse("30.05.2005");
        Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005");
        assertEquals(service.getDaysBetween(date,date1), -1L);
    }

    @Test
    public void shouldBeNormal() throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005");
        Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse("30.05.2005");
        assertEquals(service.getDaysBetween(date,date1), 1L);
    }

}

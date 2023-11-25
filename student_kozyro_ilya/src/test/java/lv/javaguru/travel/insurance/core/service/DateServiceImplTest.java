package lv.javaguru.travel.insurance.core.service;

import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DateServiceImplTest {

    DateServiceImpl dateService = new DateServiceImpl();
    String testFormat = "yyyy-MM-dd";
    @Test
    void createDateTestBadFormat1() throws ParseException {
        Exception exception = assertThrows(ParseException.class, () -> {
            dateService.createDate("Strange Data", testFormat);
        });
        assertSame(exception.getClass(), ParseException.class);

    }

    @Test
    void createDateTestBadFormat2() throws ParseException {
        Exception exception = assertThrows(ParseException.class, () -> {
            dateService.createDate("2000.10.10", testFormat);
        });

        assertSame(exception.getClass(), ParseException.class);
    }

    @Test
    void calculateDaysBetween() throws ParseException {
        BigDecimal days = dateService.getDaysBetween(dateService.createDate("2000-10-10", testFormat), dateService.createDate("2000-10-11", testFormat));
        assertEquals(BigDecimal.ONE, days);
    }

}

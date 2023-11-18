package lv.javaguru.travel.insurance.core.service;

import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DateServiceImplTest {

    @Autowired
    DateServiceImpl dateService;

    @Test
    void createDateTestBadFormat1() throws ParseException {
        Exception exception = assertThrows(ParseException.class, () -> {
            dateService.createDate("Strange Data");
        });
        assertSame(exception.getClass(), ParseException.class);

    }

    @Test
    void createDateTestBadFormat2() throws ParseException {
        Exception exception = assertThrows(ParseException.class, () -> {
            dateService.createDate("2000.10.10");
        });

        assertSame(exception.getClass(), ParseException.class);
    }

    @Test
    void calculateDaysBetween() throws ParseException {
        BigDecimal days = dateService.getDaysBetween(dateService.createDate("2000-10-10"), dateService.createDate("2000-10-11"));
        assertEquals(BigDecimal.ONE, days);
    }
}

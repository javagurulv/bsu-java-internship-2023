package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DateTimeServiceTest {


    DateTimeService dateTimeService = new DateTimeService();
    @Test
    void positiveDifferenceBetweenDates() {
        long diff = 10;
        Date date1 = createDate("03.12.2023");
        Date date2 = createDate("13.12.2023");
        assertThat(dateTimeService.getDaysBetween(date1, date2)).isEqualTo(diff);
    }

    @Test
    void negativeDifferenceBetweenDates() {
        long diff = -10;
        Date date1 = createDate("13.12.2023");
        Date date2 = createDate("03.12.2023");
        assertThat(dateTimeService.getDaysBetween(date1, date2)).isEqualTo(diff);
    }

    @Test
    void zeroDifferenceBetweenDates() {
        Date date1 = createDate("03.12.2023");
        Date date2 = createDate("03.12.2023");
        assertThat(dateTimeService.getDaysBetween(date1, date2)).isEqualTo(0L);
    }


    private static Date createDate(String simpleDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(simpleDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

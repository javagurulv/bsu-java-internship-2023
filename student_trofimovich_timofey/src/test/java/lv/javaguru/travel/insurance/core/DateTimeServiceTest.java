package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DateTimeServiceTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    @Test
    void daysBetweenEqualDatesShouldBeZero() {
        Date dateFrom = createDate("12.05.2020");
        Date dateTo = createDate("12.05.2020");
        assertThat(dateTimeService.getDaysBetween(dateFrom, dateTo)).isEqualTo(0);
    }

    @Test
    void daysBetweenDatesShouldBePositive() {
        Date dateFrom = createDate("12.05.2020");
        Date dateTo = createDate("13.05.2020");
        assertThat(dateTimeService.getDaysBetween(dateFrom, dateTo)).isEqualTo(1);
    }

    @Test
    void daysBetweenDatesShouldBeNegative() {
        Date dateFrom = createDate("13.05.2020");
        Date dateTo = createDate("12.05.2020");
        assertThat(dateTimeService.getDaysBetween(dateFrom, dateTo)).isEqualTo(-1);
    }

    private Date createDate(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

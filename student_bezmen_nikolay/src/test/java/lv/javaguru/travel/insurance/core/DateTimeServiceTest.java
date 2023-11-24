package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DateTimeServiceTest {
    private static DateTimeService dateTimeService;

    @BeforeAll static void setup(){
        dateTimeService = new DateTimeService();
    }
    @Test
    void test_correct_work_if_date_rom_is_equal_date_to() throws ParseException {

        Date from = createDate("2000-02-02");
        Date to = createDate("2000-02-02");

        var daysBeween = dateTimeService.getDaysBetween(from, to);

        assertThat(daysBeween).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("getDifferentDate")
    void test_correct_work_for_default_case(String fromDate, String toDate, long correctCountDaysBetween){
        Date from = createDate(fromDate);
        Date to = createDate(toDate);

        var daysBetween = dateTimeService.getDaysBetween(from, to);

        assertThat(daysBetween).isEqualTo(correctCountDaysBetween);
    }

    private static Stream<Arguments> getDifferentDate(){
        return Stream.of(
                Arguments.of("2000-10-01","2000-10-16", 15L),
                Arguments.of("2017-10-01","2017-10-11", 10L),
                Arguments.of("2021-05-01","2021-06-31", 61L),
                Arguments.of("1999-12-31","2000-01-16", 16L)
        );
    }

    private Date createDate(String dateForParse){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateForParse);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

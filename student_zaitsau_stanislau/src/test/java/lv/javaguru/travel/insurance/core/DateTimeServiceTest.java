package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;

public class DateTimeServiceTest {
    private final DateFormat dateFormat = DateTimeService.getDateFormat();
    @ParameterizedTest
    @CsvSource({
        "05.02.2004, 06.02.2004, 1",
        "10.02.2015, 23.12.2018, 1412",
        "21.10.2000, 01.01.2021, 7377"
    })
    public void getDaysDifferenceTest(String dateFrom, String dateTo, String ans)
        throws ParseException {
        Assertions.assertEquals(new BigDecimal(ans), DateTimeService.getDaysDifference(dateFormat.parse(dateFrom), dateFormat.parse(dateTo)));
    }
}

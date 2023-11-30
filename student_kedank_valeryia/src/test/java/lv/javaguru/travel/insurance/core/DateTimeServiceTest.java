package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldReturnZeroAgreementPrice() {
        Date dateFrom = createDate("01.01.2022");
        Date dateTo = createDate("01.01.2022");
        var daysBetween = dateTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysBetween, 0);
    }

    @Test
    public void shouldReturnPositiveAgreementPrice() {
        Date dateFrom = createDate("01.01.2022");
        Date dateTo = createDate("01.01.2023");
        Calendar calendar = Calendar.getInstance();
        var daysBetween = dateTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysBetween, 365);
    }

    @Test
    public void shouldReturnNegativeAgreementPrice() {
        Date dateFrom = createDate("01.01.2023");
        Date dateTo = createDate("01.01.2022");
        var daysBetween = dateTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysBetween, -365);
    }

    public Date createDate(String strDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

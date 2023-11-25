package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {
    private final DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldReturnZero() {
        Date agreementDateFrom = dateTimeService.createDate("22.10.2023");
        Date agreementDateTo = dateTimeService.createDate("22.10.2023");
        assertEquals(dateTimeService.getDays(agreementDateFrom, agreementDateTo), 0);
    }

    @Test
    public void shouldReturnPositive() {
        Date agreementDateFrom = dateTimeService.createDate("12.10.2023");
        Date agreementDateTo = dateTimeService.createDate("22.10.2023");
        assertEquals(dateTimeService.getDays(agreementDateFrom, agreementDateTo), 10);
    }

    @Test
    public void shouldReturnNegative() {
        Date agreementDateFrom = dateTimeService.createDate("22.10.2023");
        Date agreementDateTo = dateTimeService.createDate("02.09.2023");
        assertEquals(dateTimeService.getDays(agreementDateFrom, agreementDateTo), -50);
    }
}

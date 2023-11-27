package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

public class DataTimeServiceTest {
    private DataTimeService dataTimeService = new DataTimeService();
    @Test
    public void dateDifferenceTest1() {
        Date dateFrom = new Date(2023, 11, 17);
        Date dateTo = new Date(2023, 11, 24);
        long daysDiff = dataTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysDiff, 7L);
    }

    @Test
    public void dateDifferenceTest2() {
        Date dateFrom = new Date(2023, 11, 1);
        Date dateTo = new Date(2023, 11, 2);
        long daysDiff = dataTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysDiff, 1L);
    }

    @Test
    public void dateDifferenceTest3() {
        Date dateFrom = new Date(2023, 11, 17);
        Date dateTo = new Date(2023, 11, 17);
        long daysDiff = dataTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysDiff, 0L);
    }

    @Test
    public void dateDifferenceTest4() {
        Date dateFrom = new Date(2023, 11, 24);
        Date dateTo = new Date(2023, 11, 17);
        long daysDiff = dataTimeService.getDaysBetween(dateFrom, dateTo);
        assertEquals(daysDiff, -7L);
    }
}

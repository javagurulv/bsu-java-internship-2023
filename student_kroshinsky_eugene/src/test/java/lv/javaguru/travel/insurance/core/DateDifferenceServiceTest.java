package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
class DateDifferenceServiceTest {
    DateDifferenceService dateDifferenceService = new DateDifferenceService();

    @Test
    public void calculateDateDifferenceTest1(){
        Date dateTo = new Date(2023, 11, 12);
        Date dateFrom = new Date(2023, 11, 01);
        assertEquals(new BigDecimal(11), dateDifferenceService.calculateDateDifference(dateFrom, dateTo));
    }
    @Test
    public void calculateDateDifferenceTest2(){
        Date dateTo = new Date(2023, 11, 01);
        Date dateFrom = new Date(2023, 11, 12);
        assertEquals(new BigDecimal(-11), dateDifferenceService.calculateDateDifference(dateFrom, dateTo));
    }
    @Test
    public void calculateDateDifferenceTest3(){
        Date dateTo = new Date(2023, 11, 12);
        Date dateFrom = new Date(2023, 11, 12);
        assertEquals(new BigDecimal(0), dateDifferenceService.calculateDateDifference(dateFrom, dateTo));
    }
}

package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class DateDifferenceServiceTest {
    DateDifferenceService dateDifferenceService = new DateDifferenceService();
    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
    @Test
    void calculateDateDifferenceTestPositive(){
        Date dateTo = createDate("12.11.2023");
        Date dateFrom = createDate("01.11.2023");
        assertEquals(new BigDecimal(11), dateDifferenceService.calculateDateDifference(dateFrom, dateTo));
    }
    @Test
    void calculateDateDifferenceTestNegative(){
        Date dateTo = createDate("01.11.2023");
        Date dateFrom = createDate("12.11.2023");
        assertEquals(new BigDecimal(-11), dateDifferenceService.calculateDateDifference(dateFrom, dateTo));
    }
    @Test
    void calculateDateDifferenceTestEqual(){
        Date dateTo = createDate("12.11.2023");
        Date dateFrom = createDate("12.11.2023");
        assertEquals(new BigDecimal(0), dateDifferenceService.calculateDateDifference(dateFrom, dateTo));
    }
}

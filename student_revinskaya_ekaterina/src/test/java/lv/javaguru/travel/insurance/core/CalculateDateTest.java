package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateDateTest {
    CalculatorDate calculatorDate = new CalculatorDate();
@Test
    public void calculateWithRightDatesTest(){
       BigDecimal num = calculatorDate.calculateDiffBetweenDays(createDate("02.10.2023"),createDate( "12.10.2023"));
        assertEquals(num, BigDecimal.valueOf(10));
}
    @Test
    public void calculateWithWrongDatesTest(){
        BigDecimal num = calculatorDate.calculateDiffBetweenDays(createDate("22.10.2023"),createDate( "12.10.2023"));
        assertEquals(num, BigDecimal.valueOf(-10));
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

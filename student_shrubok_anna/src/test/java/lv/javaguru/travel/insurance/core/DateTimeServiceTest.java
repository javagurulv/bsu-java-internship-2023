package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    @Mock
    private DateTimeService dateTimeService;

    @BeforeEach
    void rewrite() { dateTimeService = new DateTimeService();}

    @Test
    void returnsCorrectResultWithPositiveDifference()
    {
        Date date1 = createDate("14-04-2005");
        Date date2 = createDate("14-04-2006");
        long days = dateTimeService.getDaysBetween(date2,date1);
        assertEquals(365, days);
    }

    @Test
    void returnsCorrectResultWithNegativeDifference()
    {
        Date date1 = createDate("14-04-2005");
        Date date2 = createDate("14-04-2006");
        long days = dateTimeService.getDaysBetween(date1,date2);
        assertEquals(-365, days);
    }

    @Test
    void returnsCorrectResultWithZeroDifference()
    {
        Date date1 = createDate("14-04-2005");
        Date date2 = createDate("14-04-2005");
        long days = dateTimeService.getDaysBetween(date2,date1);
        assertEquals(0, days);
    }

    Date createDate(String stringDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            return  formatter.parse(stringDate);
        }
        catch(ParseException e)
        {
            throw new RuntimeException();
        }
    }
}

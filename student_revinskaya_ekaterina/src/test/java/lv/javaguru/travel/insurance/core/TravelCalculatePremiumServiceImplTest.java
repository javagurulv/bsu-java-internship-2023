package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;
class TravelCalculatePremiumServiceImplTest {

    @Test
    public void responseShouldContainErrorTest() {
        TravelCalculatePremiumServiceImpl serviceImpl = new TravelCalculatePremiumServiceImpl();
        GregorianCalendar date1 = new GregorianCalendar();
        date1.set(2018, Calendar.JULY, 4);
        GregorianCalendar date2 = new GregorianCalendar();
        date1.set(2018, Calendar.AUGUST, 23);
        TravelCalculatePremiumResponse response = serviceImpl.calculatePremium(
                new TravelCalculatePremiumRequest("","Petrov", date1.getTime(),date2.getTime()));
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "personFirstName");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseNotContainErrorTest() {
        TravelCalculatePremiumServiceImpl serviceImpl = new TravelCalculatePremiumServiceImpl();
        GregorianCalendar date1 = new GregorianCalendar();
        date1.set(2018, Calendar.JULY, 4);
        GregorianCalendar date2 = new GregorianCalendar();
        date1.set(2018, Calendar.AUGUST, 23);
        TravelCalculatePremiumResponse response = serviceImpl.calculatePremium(
                new TravelCalculatePremiumRequest("Misha","Petrov", date1.getTime(),date2.getTime()));
        assertEquals(response.getErrors().size(), 0);
    }

}
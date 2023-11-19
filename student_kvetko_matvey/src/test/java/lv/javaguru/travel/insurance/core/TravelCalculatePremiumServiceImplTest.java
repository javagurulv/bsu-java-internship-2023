package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void checkResponse() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 23);
        Date agreementDateFrom = calendar.getTime();

        calendar.set(2021, Calendar.MARCH, 2);
        Date agreementDateTo = calendar.getTime();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Oleg", "Badeth", agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse("Oleg", "Badeth", agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        assertEquals(response, service.calculatePremium(request));
    }
}
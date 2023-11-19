package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
   public void Check_Step_1()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Vlad",
                                        "Leonchik",
                                        new Date(2004,9,28),
                                        new Date() );
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse("Vlad",
                "Leonchik",
                new Date(2004,9,28),
                new Date() );
        TravelCalculatePremiumServiceImpl temp = new TravelCalculatePremiumServiceImpl();
        assertEquals(temp.calculatePremium(request), response);

    }


}
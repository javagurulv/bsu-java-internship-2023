package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import java.util.Date;
import org.junit.jupiter.api.Test;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void TravelCalculatePremiumServiceImplTest() {

        TravelCalculatePremiumServiceImpl testedObject = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("first Name", "last Name", new Date(), new Date());
        TravelCalculatePremiumResponse response = testedObject.calculatePremium(request);
//        assertEqual(request.getPersonFirstName(), response.getPersonFirstName());
        System.out.println(request.getPersonFirstName().equals(response.getPersonFirstName()));
    }
}
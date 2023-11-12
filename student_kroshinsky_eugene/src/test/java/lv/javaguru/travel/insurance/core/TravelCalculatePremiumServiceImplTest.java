package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    private static TravelCalculatePremiumRequest request;
    @BeforeAll
    public static void setUp(){
        request = new TravelCalculatePremiumRequest(
                "Eugene",
                "Kroshinsky",
                new Date(01, 11, 2023),
                new Date(12 ,11, 2023)
        );
    }
    @Test
    public void calculatePremiumTest() {
        TravelCalculatePremiumServiceImpl calculator = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

}
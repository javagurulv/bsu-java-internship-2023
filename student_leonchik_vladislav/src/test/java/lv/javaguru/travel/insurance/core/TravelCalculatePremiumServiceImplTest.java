package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
   public void days_should_be_zero()
    {
       TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Vlad",
                                                                                "Leonchik",
                                                                                new Date(10, 10, 2004),
                                                                                new Date(10, 10, 2004));
       var temp = new TravelCalculatePremiumServiceImpl().calculatePremium(request);
        assertEquals(temp.getAgreementPrice(), new BigDecimal(0));
    }
    @Test
    public void pluralDifference()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Vlad",
                "Leonchik",
                new Date(2004, 10, 01),
                new Date(2004, 10, 10));
        var temp = new TravelCalculatePremiumServiceImpl().calculatePremium(request);
        assertEquals(temp.getAgreementPrice(), new BigDecimal(9));
    }
    @Test
    public void MinusDifference()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Vlad",
                "Leonchik",
                new Date(2004, 10, 10),
                new Date(2004, 10, 01));
        var temp = new TravelCalculatePremiumServiceImpl().calculatePremium(request);
        assertEquals(temp.getAgreementPrice(), new BigDecimal(-9));
    }
    @Test
    public void differenceInYear()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Vlad",
                "Leonchik",
                new Date(2005, 9, 1),
                new Date(2004, 9, 1));
        var temp = new TravelCalculatePremiumServiceImpl().calculatePremium(request);
        assertEquals(temp.getAgreementPrice(), new BigDecimal(-365));
    }



}
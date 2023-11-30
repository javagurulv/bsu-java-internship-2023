package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

class TravelCalculatePremiumServiceImplTest {
    DateTimeService date = new DateTimeService();
    @Test
    public void test()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Anton",
                "Bosko", date.createDate("15.08.2004"), date.createDate("15.08.2005"));
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse(request);
        TravelCalculatePremiumServiceImpl impl = new TravelCalculatePremiumServiceImpl();
        assert (response.equals(impl.calculatePremium(request)));
    }
}
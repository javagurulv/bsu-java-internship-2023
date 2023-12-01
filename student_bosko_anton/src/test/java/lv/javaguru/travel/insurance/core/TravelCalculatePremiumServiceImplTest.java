package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    DateTimeService date = new DateTimeService();

    @Test
    public void test_first() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Anton",
                "Bosko", date.createDate("15.08.2004"), date.createDate("15.08.2005"));
        TravelCalculatePremiumServiceImpl impl = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = impl.buildResponse(request, impl.getUnderwriting().calculatePremium(request));
        assertEquals(response.getAgreementPrice(), new BigDecimal(date.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())));
    }
    @Test
    public void test_second() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ludmila",
                "Velitskaya", date.createDate("16.05.2003"), date.createDate("15.08.2005"));
        TravelCalculatePremiumServiceImpl impl = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = impl.buildResponse(request, impl.getUnderwriting().calculatePremium(request));
        assertEquals(response.getAgreementPrice(), new BigDecimal(date.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())));
    }
}
package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void checkResponse() {
        Date agreementDateFrom = dateTimeService.createDate("20.10.2023");
        Date agreementDateTo = dateTimeService.createDate("23.10.2023");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Denis", "Lebedev",
                agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse("Denis", "Lebedev",
                agreementDateFrom, agreementDateTo, new BigDecimal("3"));
        TravelCalculatePremiumService service = new TravelCalculatePremiumServiceImpl();

        assertEquals(response, service.calculatePremium(request));
    }
}
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void checkResponse() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, Calendar.NOVEMBER, 20);
        Date agreementDateFrom = calendar.getTime();

        calendar.set(2023, Calendar.NOVEMBER, 23);
        Date agreementDateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Denis", "Lebedev",
                agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse("Denis", "Lebedev",
                agreementDateFrom, agreementDateTo);
        response.setAgreementPrice(new BigDecimal("3"));
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        assertEquals(response, service.calculatePremium(request));
    }

}
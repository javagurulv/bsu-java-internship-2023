package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumController;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void test_step5() {

        Calendar calendar = Calendar.getInstance();

        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Valeryia",
                "Kedank", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse("Valeryia",
                "Kedank", dateFrom, dateTo, BigDecimal.valueOf(365));

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse resp_done = service.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), resp_done.getPersonFirstName());
        assertEquals(response.getPersonLastName(), resp_done.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), resp_done.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), resp_done.getAgreementDateTo());

        assertEquals(response.getAgreementPrice(), resp_done.getAgreementPrice());

    }

}
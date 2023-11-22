package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    void testSt2() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2021, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();

        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan",
                "Ivanov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName("Ivan");
        response.setPersonLastName("Ivanov");
        response.setAgreementDateFrom(dateFrom);
        response.setAgreementDateTo(dateTo);

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse resp_done = service.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), resp_done.getPersonFirstName());
        assertEquals(response.getPersonLastName(), resp_done.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), resp_done.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), resp_done.getAgreementDateTo());

        long diffInMillies = Math.abs(request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime());
        long diffDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        assertEquals(BigDecimal.valueOf(diffDays), resp_done.getAgreementPrice());
    }

}
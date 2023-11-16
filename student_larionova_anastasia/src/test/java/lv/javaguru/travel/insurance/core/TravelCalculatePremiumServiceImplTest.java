package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void Test_Step_2() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ivan", "Ivanov", new Date(200L), new Date(2200L), new BigDecimal(0));
        new TravelCalculatePremiumResponse();
        TravelCalculatePremiumResponse response;
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        response = service.calculatePremium(request);
        response.calculateAgreementPrice();
        assert (   Objects.equals(request.getPersonFirstName(), response.getPersonFirstName())
                && Objects.equals(request.getPersonLastName(), response.getPersonLastName())
                && request.getAgreementDateFrom() == response.getAgreementDateFrom()
                && request.getAgreementDateTo() == response.getAgreementDateTo()
                && Objects.equals(request.getAgreementPrice(), response.getAgreementPrice()));
    }

}
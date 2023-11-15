package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void Test_Step_2() {
        new TravelCalculatePremiumResponse();
        TravelCalculatePremiumResponse response;
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ivanov", "Ivan", new Date(200L), new Date(2000L));
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        response = service.calculatePremium(request);
        assert (Objects.equals(request.getPersonFirstName(), response.getPersonFirstName())
                && Objects.equals(request.getPersonLastName(), response.getPersonLastName())
                && request.getAgreementDateFrom() == response.getAgreementDateFrom()
                && request.getAgreementDateTo() == response.getAgreementDateTo());
    }

}
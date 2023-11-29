package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void test() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Anton",
                "Bosko", new Date(123123100123L), new Date(123123123123L));
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse(request);
        TravelCalculatePremiumServiceImpl impl = new TravelCalculatePremiumServiceImpl();
        assert (Objects.equals(response.getPersonFirstName(), impl.calculatePremium(request).getPersonFirstName()) &&
                Objects.equals(response.getPersonLastName(), impl.calculatePremium(request).getPersonLastName()) &&
                response.getAgreementDateFrom() == impl.calculatePremium(request).getAgreementDateFrom() &&
                response.getAgreementDateTo() == impl.calculatePremium(request).getAgreementDateTo());
    }
}
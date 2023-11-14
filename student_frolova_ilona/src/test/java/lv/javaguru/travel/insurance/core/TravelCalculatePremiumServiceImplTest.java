package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumServiceImpl service;

    TravelCalculatePremiumServiceImplTest() {
        service = new TravelCalculatePremiumServiceImpl();
        service.setDateTimeService(new DateTimeService());
    }

    @Test
    public void responseParametersEqualToRequestParametersWhenReturnedByController() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Surname", "Name", new Date(12L), new Date(1212L)
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assert(Objects.equals(response.getPersonLastName(), request.getPersonLastName()) &&
                Objects.equals(response.getPersonFirstName(), request.getPersonFirstName()) &&
                response.getAgreementDateFrom() == request.getAgreementDateFrom() &&
                response.getAgreementDateTo() == request.getAgreementDateTo()
             );
    }
}
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void responseParametersEqualToRequestParametersWhenReturnedByController() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
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

    @Test
    public void calculatingAgreementPriceAsDifferenceInDays() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Surname", "Name", new Date(12L), new Date(172800012L)
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assert(response.getAgreementPrice().compareTo(new BigDecimal("2.0")) == 0);
    }

}
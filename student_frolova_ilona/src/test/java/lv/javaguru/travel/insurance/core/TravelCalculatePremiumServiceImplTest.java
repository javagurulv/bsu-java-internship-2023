package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                "Surname", "Name", new Date(12L), new Date(129600018L)
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        BigDecimal result = response.getAgreementPrice().setScale(5, RoundingMode.HALF_EVEN);

        assert(result.compareTo(new BigDecimal("1.5")) == 0);
    }

}
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void TravelCalculatePremiumServiceImplTest() {
        Date dateTo = new Date(2002, 2, 2);
        Date dateFrom = new Date(2001, 1, 1);

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("first name",
                "last name", dateFrom, dateTo);

        TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
        travelCalculatePremiumService.calculatePremium(request);

        assertThat(request.getPersonLastName()).isEqualTo("last name");
        assertThat(request.getPersonFirstName()).isEqualTo("first name");
        assertThat(request.getAgreementDateTo()).isEqualTo(dateTo);
        assertThat(request.getAgreementDateFrom()).isEqualTo(dateFrom);

    }

}
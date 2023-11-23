package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void test_correct_set_field() {
        TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
        String firstName = "Nikolay";
        String lastName = "Bezmen";
        Date dateFrom = new Date(2002, Calendar.AUGUST, 11);
        Date dateTo = new Date(2002, Calendar.SEPTEMBER, 11);
        TravelCalculatePremiumRequest request =
                new TravelCalculatePremiumRequest(firstName, lastName, dateFrom, dateTo);

        TravelCalculatePremiumResponse resultOfWork = travelCalculatePremiumService.calculatePremium(request);

        assertThat(resultOfWork.getAgreementDateFrom()).isEqualTo(request.getAgreementDateFrom());
        assertThat(resultOfWork.getAgreementDateTo()).isEqualTo(request.getAgreementDateTo());
        assertThat(resultOfWork.getPersonFirstName()).isEqualTo(request.getPersonFirstName());
        assertThat(resultOfWork.getPersonLastName()).isEqualTo(request.getPersonLastName());
    }

}
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void testCalculatePremium() {
        TravelCalculatePremiumServiceImpl calculatePremiumService = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest(
                "Alexandr",
                "Krutoi",
                Date.valueOf(LocalDate.of(2023, 11, 23)),
                Date.valueOf(LocalDate.of(2023, 11, 30))
        );

        TravelCalculatePremiumResponse calculatePremiumResponse = calculatePremiumService.calculatePremium(premiumRequest);

        assertAll(
                () -> assertThat(calculatePremiumResponse.getPersonFirstName()).isEqualTo(premiumRequest.getPersonFirstName()),
                () -> assertThat(calculatePremiumResponse.getPersonLastName()).isEqualTo(premiumRequest.getPersonLastName()),
                () -> assertThat(calculatePremiumResponse.getAgreementDateFrom()).isEqualTo(premiumRequest.getAgreementDateFrom()),
                () -> assertThat(calculatePremiumResponse.getAgreementDateTo()).isEqualTo(premiumRequest.getAgreementDateTo())
        );
    }

}
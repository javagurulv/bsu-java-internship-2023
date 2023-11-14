package lv.javaguru.travel.insurance.core;


import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TravelCalculatePremiumServiceImplTest {

    private static final TravelCalculatePremiumRequest REQUEST
            = new TravelCalculatePremiumRequest("Timofey", "Trofimovich",
            new GregorianCalendar(2023, Calendar.NOVEMBER, 10).getTime(), new GregorianCalendar(2023, Calendar.NOVEMBER, 13).getTime());
    private static final TravelCalculatePremiumService CALCULATE_PREMIUM_SERVICE = new TravelCalculatePremiumServiceImpl() {
    };

    @Test
    @Tag("response")
    public void responseHasTheSameFieldsAsRequest() {
        TravelCalculatePremiumResponse response = CALCULATE_PREMIUM_SERVICE.calculatePremium(REQUEST);
        assertAll(
                () -> assertThat(response.getPersonFirstName()).isEqualTo(REQUEST.getPersonFirstName()),
                () -> assertThat(response.getPersonLastName()).isEqualTo(REQUEST.getPersonLastName()),
                () -> assertThat(response.getAgreementDateFrom()).isEqualTo(REQUEST.getAgreementDateFrom()),
                () -> assertThat(response.getAgreementDateTo()).isEqualTo(REQUEST.getAgreementDateTo())
        );



    }

}
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class TravelCalculatePremiumServiceImplTest {


    @Autowired
    TravelCalculatePremiumService service;

    Date agreementDateFrom = new Date(2020, Calendar.DECEMBER,11);
    Date getAgreementDateTo =  new Date(2023, Calendar.DECEMBER,11);

    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
            "Bill",
            "Morrigan",
            agreementDateFrom,
            getAgreementDateTo );
    TravelCalculatePremiumResponse response;

    @Test
    void calculatePremiumTest() {
        response = this.service.calculatePremium(request);
        assertThat(response.getPersonFirstName()).isEqualTo(request.getPersonFirstName());
        assertThat(response.getPersonLastName()).isEqualTo(request.getPersonLastName());
        assertThat(response.getAgreementDateFrom()).isEqualTo(request.getAgreementDateFrom());
        assertThat(response.getAgreementDateTo()).isEqualTo(request.getAgreementDateTo());
        long daysBetween = (request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime()) / 1000 * 60 * 60 * 24;
        assertThat(response.getAgreementPrice()).isEqualTo(new BigDecimal(daysBetween));
    }





}
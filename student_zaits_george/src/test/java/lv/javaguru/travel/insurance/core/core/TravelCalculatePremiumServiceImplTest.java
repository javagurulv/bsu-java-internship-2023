package lv.javaguru.travel.insurance.core.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.core.utils.ConverterTestUtils.buildPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TravelCalculatePremiumServiceImpl.class)
class TravelCalculatePremiumServiceImplTest {

    @Autowired
    private TravelCalculatePremiumService travelCalculatePremiumService;

    @Test
    void calculatePremiumShouldReturnResponseWithSameFields() {
        var request = buildPojo(TravelCalculatePremiumRequest.class);

        var response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime()));
    }

}
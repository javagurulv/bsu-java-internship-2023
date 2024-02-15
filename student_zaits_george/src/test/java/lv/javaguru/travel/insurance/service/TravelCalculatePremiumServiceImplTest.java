package lv.javaguru.travel.insurance.service;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.service.impl.TravelCalculatePremiumServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.utils.ConverterTestUtils.buildPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TravelCalculatePremiumServiceImpl.class)
@MockBeans(@MockBean(TravelCalculatePremiumRequestValidator.class))
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
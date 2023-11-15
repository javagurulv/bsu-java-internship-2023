package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    private static BigDecimal agreementPrice;
    private static TravelCalculatePremiumRequest request;
    @BeforeAll
    public static void init(){
        request = new TravelCalculatePremiumRequest(
                "Eugene",
                "Kroshinsky",
                new Date(2023, 11, 01),
                new Date(2023 ,11, 12)
        );
        agreementPrice = new BigDecimal(11);
    }
    @Test
    public void calculatePremiumTest() {
        TravelCalculatePremiumServiceImpl calculator = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertEquals(agreementPrice, response.getAgreementPrice());
    }

}
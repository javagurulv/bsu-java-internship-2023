package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.javaguru.travel.insurance.core.api.dto.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static lv.javaguru.travel.insurance.core.validations.calculate.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class TravelRiskPremiumCalculatorThirdPartyLiabilityTest {
    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;

    @InjectMocks
    TravelRiskPremiumCalculatorThirdPartyLiability calculator;

    @Test
    public void calculatePremiumIntegrationTest() {
        PersonDTO person = createPersonDTO().build();
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2026-09-11"))
                .withDateTo(createDate("2026-09-12"))
                .withSelectedRisks("TRAVEL_THIRD_PARTY_LIABILITIES")
                .build();
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }
}

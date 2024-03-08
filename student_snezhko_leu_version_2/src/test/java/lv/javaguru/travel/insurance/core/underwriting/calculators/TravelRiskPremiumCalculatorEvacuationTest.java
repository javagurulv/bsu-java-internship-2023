package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static lv.javaguru.travel.insurance.core.validations.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TravelRiskPremiumCalculatorEvacuationTest {
    @Mock
    private AgreementDTO agreement;
    @Mock
    private PersonDTO person;

    @InjectMocks
    TravelRiskPremiumCalculatorEvacuation calculator;

    @Test
    public void calculatePremiumIntegrationTest() {
        PersonDTO person = createPersonDTO().build();
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2026-09-11"))
                .withDateTo(createDate("2026-09-12"))
                .withSelectedRisks("TRAVEL_EVACUATION")
                .build();
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }
}

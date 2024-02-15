package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRiskPremiumCalculatorSportActivitiesTest {
    private TravelRiskPremiumCalculatorSportActivities calculator = new TravelRiskPremiumCalculatorSportActivities();
    private AgreementDTO agreement;
    private PersonDTO person;
    @BeforeEach
    public void init() {
        agreement = mock(AgreementDTO.class);
        List<String> risks = new ArrayList<>();
        risks.add(calculator.getIc());
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2026-09-12"));
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2026-09-11"));
        when(agreement.getSelectedRisks()).thenReturn(risks);

        person = mock(PersonDTO.class);
    }

    @Test
    public void calculatePremiumTest() {
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }
}

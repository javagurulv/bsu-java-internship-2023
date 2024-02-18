package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TravelRiskPremiumCalculatorCancellationTest {
    TravelRiskPremiumCalculatorCancellation calculator = new TravelRiskPremiumCalculatorCancellation();
    @Mock
    private AgreementDTO agreement;
    @Mock
    private PersonDTO person;

    @BeforeEach
    public void init() {
        List<String> risks = new ArrayList<>();
        risks.add(calculator.getIc());
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2026-09-12"));
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2026-09-11"));
        when(agreement.getSelectedRisks()).thenReturn(risks);

    }

    @Test
    public void calculatePremiumTest() {
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }
}

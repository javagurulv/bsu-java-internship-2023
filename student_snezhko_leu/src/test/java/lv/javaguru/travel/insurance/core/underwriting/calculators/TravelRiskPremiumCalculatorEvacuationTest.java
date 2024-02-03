package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelRiskPremiumCalculatorEvacuationTest {
    TravelRiskPremiumCalculatorEvacuation calculator = new TravelRiskPremiumCalculatorEvacuation();
    TravelCalculatePremiumRequestV1 request;
    @BeforeEach
    public void init() {
        request = mock(TravelCalculatePremiumRequestV1.class);
        List<String> risks = new ArrayList<>();
        risks.add(calculator.getIc());
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
        when(request.getSelectedRisks()).thenReturn(risks);
    }

    @Test
    public void calculatePremiumTest() {
        assertEquals(calculator.calculatePremium(request), BigDecimal.ZERO);
    }
}

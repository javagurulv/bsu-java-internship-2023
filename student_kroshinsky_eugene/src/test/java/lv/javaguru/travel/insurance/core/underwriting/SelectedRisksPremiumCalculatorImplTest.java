package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class SelectedRisksPremiumCalculatorImplTest {
    @Mock private TravelRiskPremiumCalculator riskCalculator;
    @Mock private TravelCalculatePremiumRequest request;
    @InjectMocks private SelectedRisksPremiumCalculatorImpl selectedRisksCalculator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(riskCalculator);
        assertNotNull(request);
        assertNotNull(selectedRisksCalculator);
    }
    @Test
    void calculateTravelRisksList() {
        when(riskCalculator.calculatePremium(request)).thenReturn(new BigDecimal(10));
        when(riskCalculator.getRiskIc()).thenReturn("ic");
        when(request.getSelectedRisks()).thenReturn(List.of("ic"));
        List<TravelRiskPremiumCalculator> expextedList = List.of(riskCalculator);
        ReflectionTestUtils.setField(selectedRisksCalculator, "calculators", expextedList);

        List<TravelRisk> result = List.of(new TravelRisk("ic", new BigDecimal(10)));
        assertEquals(result, selectedRisksCalculator.calculateTravelRisksList(request));
    }
}
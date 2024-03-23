package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    @Mock
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculatorMock;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwritingImpl;

    @Test
    void shouldCalculateTotalPremiumAsSumOfRiskPremiums() {
        List<RiskPremium> riskPremiums = List.of(
                new RiskPremium("TRAVEL_MEDICAL", BigDecimal.ONE),
                new RiskPremium("TRAVEL_EVACUATION", BigDecimal.ONE)
        );
        when(selectedRisksPremiumCalculatorMock.calculatePremiumForRisks(requestMock)).thenReturn(riskPremiums);
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwritingImpl.calculatePremium(requestMock);
        assertEquals(premiumCalculationResult.getTotalPremium(), new BigDecimal(2));
    }
}

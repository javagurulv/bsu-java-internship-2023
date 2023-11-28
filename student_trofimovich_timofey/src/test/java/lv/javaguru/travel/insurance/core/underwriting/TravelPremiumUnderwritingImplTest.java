package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.core.underwriting.calculations.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    public void shouldReturnResponseWithCorrectTravelCalculatePremiumResult() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK_1", "RISK_2"));
        TravelRiskPremiumCalculator mock1 = mock(TravelRiskPremiumCalculator.class);
        when(mock1.getRiskIc()).thenReturn("RISK_1");
        when(mock1.calculatePremium(request)).thenReturn(new BigDecimal(10));
        TravelRiskPremiumCalculator mock2 = mock(TravelRiskPremiumCalculator.class);
        when(mock2.getRiskIc()).thenReturn("RISK_2");
        when(mock2.calculatePremium(request)).thenReturn(new BigDecimal(10));
        List<TravelRiskPremiumCalculator> premiumCalculationList = List.of(mock1, mock2);
        ReflectionTestUtils.setField(premiumUnderwriting, "premiumCalculationList", premiumCalculationList);
        TravelPremiumCalculationResult travelPremiumCalculationResult = premiumUnderwriting.calculatePremium(request);
        assertThat(travelPremiumCalculationResult.getTotalPremium()).isEqualTo(new BigDecimal(20));
        assertThat(travelPremiumCalculationResult.getRiskPremiums().size()).isEqualTo(2);
        assertThat(travelPremiumCalculationResult.getRiskPremiums().get(0).getIc()).isEqualTo("RISK_1");
        assertThat(travelPremiumCalculationResult.getRiskPremiums().get(0).getPremium()).isEqualTo(new BigDecimal(10));
        assertThat(travelPremiumCalculationResult.getRiskPremiums().get(1).getIc()).isEqualTo("RISK_2");
    }
}

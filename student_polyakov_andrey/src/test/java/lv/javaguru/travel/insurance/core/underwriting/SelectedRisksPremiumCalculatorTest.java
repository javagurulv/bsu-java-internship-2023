package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class SelectedRisksPremiumCalculatorTest {
    @Mock
    private TravelRiskPremiumCalculator riskPremiumCalculatorMock1;
    @Mock
    private TravelRiskPremiumCalculator riskPremiumCalculatorMock2;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @InjectMocks
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @BeforeEach
    public void init() {
        var travelRiskPremiumCalculatorList = List.of(riskPremiumCalculatorMock1, riskPremiumCalculatorMock2);
        ReflectionTestUtils.setField(selectedRisksPremiumCalculator, "travelRiskPremiumCalculatorList", travelRiskPremiumCalculatorList);
    }

    @Test
    void shouldCalculatePremiumForOneRisk() {
        when(riskPremiumCalculatorMock1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculatorMock1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        List<RiskPremium> riskPremiums = selectedRisksPremiumCalculator.calculatePremiumForRisks(requestMock);

        assertEquals(riskPremiums.size(), 1);
        assertEquals(riskPremiums.get(0).getRiskIc(), "TRAVEL_MEDICAL");
        assertEquals(riskPremiums.get(0).getPremium(), BigDecimal.ONE);
    }
    @Test
    void shouldCalculatePremiumForTwoRisks() {
        when(riskPremiumCalculatorMock1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculatorMock1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(riskPremiumCalculatorMock2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");
        when(riskPremiumCalculatorMock2.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_EVACUATION"));
        List<RiskPremium> riskPremiums = selectedRisksPremiumCalculator.calculatePremiumForRisks(requestMock);

        assertEquals(riskPremiums.size(), 2);
        assertEquals(riskPremiums.get(0).getRiskIc(), "TRAVEL_MEDICAL");
        assertEquals(riskPremiums.get(0).getPremium(), BigDecimal.ONE);
        assertEquals(riskPremiums.get(1).getRiskIc(), "TRAVEL_EVACUATION");
        assertEquals(riskPremiums.get(1).getPremium(), BigDecimal.ONE);
    }
    @Test
    void shouldThrowExceptionWhenSelectedRiskTypeNotSupported() {
        when(riskPremiumCalculatorMock1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculatorMock2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");
        when(requestMock.getSelected_risks()).thenReturn(List.of("FAKE_RISK_TYPE"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> selectedRisksPremiumCalculator.calculatePremiumForRisks(requestMock));
        assertEquals(exception.getMessage(), "Not supported riskIc = FAKE_RISK_TYPE");
    }
}

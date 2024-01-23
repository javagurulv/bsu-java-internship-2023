package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksPremiumCalculatorTest {
    @InjectMocks
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    private TravelRiskPremiumCalculator riskPremiumCalculator1;
    private TravelRiskPremiumCalculator riskPremiumCalculator2;

    private AgreementDTO agreement;
    private PersonDTO person;

    @BeforeEach
    void init() {
        riskPremiumCalculator1 = mock(TravelRiskPremiumCalculator.class);
        riskPremiumCalculator2 = mock(TravelRiskPremiumCalculator.class);
        agreement = mock(AgreementDTO.class);
        person = mock(PersonDTO.class);
        ReflectionTestUtils.setField(selectedRisksPremiumCalculator, "riskPremiumCalculators",
                List.of(riskPremiumCalculator1, riskPremiumCalculator2));
    }

    @Test
    void shouldThrowErrorWhenRiskIsNotSupported() {

        when(riskPremiumCalculator1.getRiskIc()).thenReturn("risk_1");
        when(riskPremiumCalculator2.getRiskIc()).thenReturn("risk_2");

        when(agreement.getSelectedRisks()).thenReturn(List.of("unsupported_risk"));

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person));
        assertThat(runtimeException.getMessage()).isEqualTo("Not supported riskIc = unsupported_risk");
    }

    @Test
    void shouldCalculatePremiumForOneRisk() {

        when(riskPremiumCalculator1.getRiskIc()).thenReturn("risk_1");
        when(riskPremiumCalculator1.calculatePremium(agreement, person)).thenReturn(new BigDecimal("10"));
        when(agreement.getSelectedRisks()).thenReturn(List.of("risk_1"));

        List<RiskDTO> riskDTOS = selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person);
        assertThat(riskDTOS.get(0).getRiskIc()).isEqualTo("risk_1");
        assertThat(riskDTOS.get(0).getPremium()).isEqualTo(new BigDecimal("10"));
    }


    @Test
    void shouldCalculatePremiumForTwoRisk() {

        when(riskPremiumCalculator1.getRiskIc()).thenReturn("risk_1");
        when(riskPremiumCalculator1.calculatePremium(agreement, person)).thenReturn(new BigDecimal("10"));

        when(riskPremiumCalculator2.getRiskIc()).thenReturn("risk_2");
        when(riskPremiumCalculator2.calculatePremium(agreement, person)).thenReturn(new BigDecimal("20"));

        when(agreement.getSelectedRisks()).thenReturn(List.of("risk_1", "risk_2"));

        List<RiskDTO> riskDTOS = selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person);

        assertThat(riskDTOS.size()).isEqualTo(2);
        assertThat(riskDTOS.get(0).getRiskIc()).isEqualTo("risk_1");
        assertThat(riskDTOS.get(0).getPremium()).isEqualTo(new BigDecimal("10"));
        assertThat(riskDTOS.get(1).getRiskIc()).isEqualTo("risk_2");
        assertThat(riskDTOS.get(1).getPremium()).isEqualTo(new BigDecimal("20"));
    }

}

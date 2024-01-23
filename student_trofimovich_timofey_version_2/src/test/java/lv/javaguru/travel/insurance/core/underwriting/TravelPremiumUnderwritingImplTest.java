package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @Mock
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    public void shouldReturnResponseWithCorrectTravelCalculatePremiumResult() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);

        RiskDTO risk1 = mock(RiskDTO.class);
        when(risk1.getPremium()).thenReturn(new BigDecimal(10));
        RiskDTO risk2 = mock(RiskDTO.class);
        when(risk2.getPremium()).thenReturn(new BigDecimal(25));

        List<RiskDTO> riskPremiums = List.of(risk1, risk2);

        when(selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person))
                .thenReturn(riskPremiums);
        assertThat(premiumUnderwriting.calculatePremium(agreement, person)
                .getTotalPremium()).isEqualTo(new BigDecimal(35));
    }
}

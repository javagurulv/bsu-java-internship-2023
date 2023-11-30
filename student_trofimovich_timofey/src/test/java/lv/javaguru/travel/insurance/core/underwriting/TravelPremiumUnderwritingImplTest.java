package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @Mock
    SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @Mock
    CountryDefaultDayRateRepository rateRepository;
    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    public void shouldReturnResponseWithCorrectTravelCalculatePremiumResult() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        RiskPremium riskPremium1 = mock(RiskPremium.class);
        when(riskPremium1.getPremium()).thenReturn(new BigDecimal(10));
        RiskPremium riskPremium2 = mock(RiskPremium.class);
        when(riskPremium2.getPremium()).thenReturn(BigDecimal.ZERO);
        List<RiskPremium> riskPremiums = List.of(riskPremium1, riskPremium2);
        when(selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request))
                .thenReturn(riskPremiums);
        when(request.getCountry()).thenReturn("country");
        CountryDefaultDayRate dayRate = mock(CountryDefaultDayRate.class);
        when(dayRate.getCountryDefaultDayRate()).thenReturn(new BigDecimal(2));
        when(rateRepository.findByCountryIc("country")).thenReturn(Optional.of(dayRate));
        assertThat(premiumUnderwriting.calculatePremium(request).getTotalPremium()).isEqualTo(new BigDecimal(20));
    }
}

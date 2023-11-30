package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{

    @Autowired
    SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @Autowired
    CountryDefaultDayRateRepository rateRepository;

    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request);
        Optional<CountryDefaultDayRate> countryRate = rateRepository.findByCountryIc(request.getCountry());
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums, countryRate.get());
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiums, CountryDefaultDayRate countryRate) {
        BigDecimal sumOfRisksPremiums = calculateSumOfRisksPremiums(riskPremiums);
        BigDecimal countryDefaultDayRate = getCountryDefaultDayRate(countryRate);
        return sumOfRisksPremiums.multiply(countryDefaultDayRate);
    }
    private BigDecimal calculateSumOfRisksPremiums(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream().map(RiskPremium::getPremium).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private BigDecimal getCountryDefaultDayRate(CountryDefaultDayRate countryRate) {
        return countryRate.getCountryDefaultDayRate();
    }
}

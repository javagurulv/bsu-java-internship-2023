package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired
    private DateTimeUtil calculateDate;
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal dayCount = calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
        BigDecimal countryDefaultDayPremium = findDefaultDayRate(request.getCountry());
        return dayCount.multiply(countryDefaultDayPremium);
    }
private BigDecimal findDefaultDayRate(String country){
    return countryDefaultDayRateRepository.findByCountryIc(country)
            .orElseThrow(()->new RuntimeException("default day rate for country "+country+" not found")).getDefaultDayRate();

}
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
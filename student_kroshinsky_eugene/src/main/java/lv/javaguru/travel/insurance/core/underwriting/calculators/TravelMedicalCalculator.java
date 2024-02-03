package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class TravelMedicalCalculator implements TravelRiskPremiumCalculator {
    @Autowired private DateTimeUtil dateTimeDifference;
    @Autowired private CountryDefaultDayRateRepository repository;
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal countryDefaultDayPremium = repository.findByIc(request.getCountry()).get().getDayRate();
        BigDecimal DayCount = dateTimeDifference.calculateDateDifference(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return countryDefaultDayPremium.multiply(DayCount);
    }
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}

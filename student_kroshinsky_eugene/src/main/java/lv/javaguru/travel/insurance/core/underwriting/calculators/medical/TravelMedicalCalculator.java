package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
class TravelMedicalCalculator implements TravelRiskPremiumCalculator {
    @Autowired private AgeCoefficientRepository ageCoefficientRepository;
    @Autowired private DateTimeUtil dateTimeDifference;
    @Autowired private CountryDefaultDayRateRepository repository;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal ageCoefficient = ageCoefficientRepository.findByAge(calculateAge(request)).get().getCoefficient();
        BigDecimal countryDefaultDayPremium = repository.findByIc(request.getCountry()).get().getDayRate();
        BigDecimal dayCount = dateTimeDifference.calculateDateDifference(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return countryDefaultDayPremium.multiply(dayCount.multiply(ageCoefficient));
    }
    private int calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate dateOfBirth = request.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}

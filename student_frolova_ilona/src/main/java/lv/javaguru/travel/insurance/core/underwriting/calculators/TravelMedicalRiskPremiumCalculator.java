package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeUtil dateTimeUtil;

    private final CountryDefaultDayRateRepository countryRepository;
    private final AgeCoefficientRepository ageRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {

        BigDecimal dayCount = dateTimeUtil.getDifferenceInDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
        Integer personAge = calculateAge(request);
        BigDecimal ageCoefficient = getAgeCoefficient(personAge);

        return countryRepository.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + request.getCountry()))
                .multiply(dayCount)
                .multiply(ageCoefficient);
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate personBirthDate = toLocalDate(request.getPersonBirthDate());
        LocalDate currentDate = toLocalDate(dateTimeUtil.getCurrentDateTime());
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private BigDecimal getAgeCoefficient(Integer personAge) {
        return ageRepository.findCoefficientByAge(personAge)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found by age = " + personAge));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}

package lv.javaguru.travel.insurance.core.underwriting.calculations;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
class TravelMedicalPremiumCalculation implements TravelRiskPremiumCalculator{
    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    CountryDefaultDayRateRepository rateRepository;
    @Autowired
    AgeCoefficientRepository ageCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        long numberOfDays = getNumberOfDays(request);
        Optional<CountryDefaultDayRate> countryRate = rateRepository.findByCountryIc(request.getCountry());
        return new BigDecimal(numberOfDays)
                .multiply(getCountryDefaultDayRate(countryRate.get()))
                .multiply(getAgeCoefficient(calculateAge(request)));
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate personBirthDate = toLocalDate(request.getDateOfBirth());
        LocalDate currentDate = toLocalDate(new Date());
        return Period.between(personBirthDate, currentDate).getYears();
    }
    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public String getRiskIc() {
       return "TRAVEL_MEDICAL";
    }

    private long getNumberOfDays(TravelCalculatePremiumRequest request) {
        return dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
    private BigDecimal getCountryDefaultDayRate(CountryDefaultDayRate countryRate) {
        return countryRate.getCountryDefaultDayRate();
    }
    private BigDecimal getAgeCoefficient(int age) {
        return ageCoefficientRepository.findCoefficient(age).get().getCoefficient();
    }
}

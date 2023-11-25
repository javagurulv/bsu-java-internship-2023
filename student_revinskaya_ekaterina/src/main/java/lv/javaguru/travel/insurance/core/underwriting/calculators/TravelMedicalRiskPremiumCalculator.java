package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired
    private DateTimeUtil calculateDate;
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal dayCount = calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
        BigDecimal countryDefaultDayPremium = findDefaultDayRate(request.getCountry());
        BigDecimal ageCoefficient = findAgeCoefficient(request.getBirthday());
        return dayCount.multiply(countryDefaultDayPremium).multiply(ageCoefficient);
    }
private BigDecimal findDefaultDayRate(String country){
    return countryDefaultDayRateRepository.findByCountryIc(country)
            .orElseThrow(()->new RuntimeException("default day rate for country "+country+" not found")).getDefaultDayRate();

}
    private BigDecimal findAgeCoefficient(Date birthday){
        Period period = Period.between(localDateFromDate(birthday), localDateFromDate(dateTimeUtil.getCurrentDateTime()));
        return ageCoefficientRepository.findByAge(period.getYears())
                .orElseThrow(()->new RuntimeException("coefficient not found for calculated age")).getCoefficient();

    }
    private LocalDate localDateFromDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
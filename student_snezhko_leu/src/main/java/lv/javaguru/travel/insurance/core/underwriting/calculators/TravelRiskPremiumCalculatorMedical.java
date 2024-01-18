package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findAge;
import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;

@Component
public class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    @Autowired
    private AgeCoefficientRepository acRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return  BigDecimal.valueOf(
                cddrRepository.findByCountryIc(
                        request.getCountry()).get().getCountryDefaultDayRate()
                * findDiffBetweenTwoDate(request.getAgreementDateTo(), request.getAgreementDateFrom())
                * acRepository
                        .findByAgeFromAndAgeTo(
                                findAge(new Date(), request.getPersonBirthDate())
                        )
                        .get()
                        .getCoefficient()
        );
                                //.get().getCountryDefaultDayRate()), findDiffBetweenTwoDate(request.getAgreementDateTo(), request.getAgreementDateFrom()));
    }

    @Override
    public String getIc() {
        return "TRAVEL_MEDICAL";
    }
}

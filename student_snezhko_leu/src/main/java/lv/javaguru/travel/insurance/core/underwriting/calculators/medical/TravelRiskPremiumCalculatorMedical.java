package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findAge;
import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;

@Component
public class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {
    @Autowired
    private TravelCalculateDayCount dayCount;

    @Autowired
    private TravelCalculateMedicalAgeCoefficient ageCoefficient;

    @Autowired
    private TravelCalculateMedicalCountryDefaultDayRate cddr;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal result = BigDecimal.valueOf(
                dayCount.calculatePremium(request)
                * ageCoefficient.calculatePremium(request)
                * cddr.calculatePremium(request)
        );
        /*
        BigDecimal result = BigDecimal.valueOf(
                cddrRepository.findByCountryIc(
                        request.getCountry()).get().getCountryDefaultDayRate()
                * findDiffBetweenTwoDate(request.getAgreementDateTo(), request.getAgreementDateFrom())
                * acRepository
                        .findByAgeFromAndAgeTo(
                                findAge(new Date(), request.getPersonBirthDate())
                        )
                        .get()
                        .getCoefficient()
        );//.setScale(2, RoundingMode.HALF_UP);
        */
        return result;

                                //.get().getCountryDefaultDayRate()), findDiffBetweenTwoDate(request.getAgreementDateTo(), request.getAgreementDateFrom()));
    }

    @Override
    public String getIc() {
        return "TRAVEL_MEDICAL";
    }
}

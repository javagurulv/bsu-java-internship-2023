package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {
    @Autowired
    private TravelCalculateDayCount dayCount;

    @Autowired
    private TravelCalculateMedicalAgeCoefficient ageCoefficient;

    @Autowired
    private TravelCalculateMedicalCountryDefaultDayRate cddr;

    @Autowired
    private TravelCalculateInsuranceLimitCoefficient mrllCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
//        Double ageCoeff = ageCoefficient.calculatePremium(request);
        //Double mrllValue = mrllCoefficientCalculator.calculatePremium(request);
        BigDecimal result = cddr.calculatePremium(request).multiply(ageCoefficient.calculatePremium(request))
                .multiply(BigDecimal.valueOf(dayCount.calculatePremium(request)))
                .multiply(mrllCoefficientCalculator.calculatePremium(request));
        /*BigDecimal.valueOf(
                //ageCoeff
                cddr.calculatePremium(request)
                * ageCoefficient.calculatePremium(request)
                * dayCount.calculatePremium(request)
                * mrllValue//mrllCoefficientCalculator.calculatePremium(request)
        );
        */
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

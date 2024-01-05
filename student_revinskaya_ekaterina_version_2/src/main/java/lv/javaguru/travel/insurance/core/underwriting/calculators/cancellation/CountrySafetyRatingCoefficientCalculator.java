package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.repositories.CountrySafetyRatingCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CountrySafetyRatingCoefficientCalculator {
    @Autowired
    private CountrySafetyRatingCoefficientRepository countrySafetyRatingCoefficientRepository;
    BigDecimal calculate(AgreementDTO agreementDTO) {
        BigDecimal countryRating = countrySafetyRatingCoefficientRepository.findByCountryIc(agreementDTO.getCountry())
                .orElseThrow(()->new RuntimeException(
                        "country "+ agreementDTO.getCountry()+" not supports by the system"))
                .getCoefficient();
        return agreementDTO.getTravelCost().
                divide(countryRating, RoundingMode.HALF_EVEN);
    }
}

package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
class AgeCoefficientCalculator {
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    @Value("${age.coefficient.enabled:false}")
    boolean ageCoefficientEnabled;
    BigDecimal getAgeCoefficient(TravelCalculatePremiumRequestV1 request) {
        return ageCoefficientEnabled ?
               calculateAgeCoefficient(request)
                : getDefaultAgeCoefficient();
    }

    private BigDecimal calculateAgeCoefficient(TravelCalculatePremiumRequestV1 request) {
        Integer age = calculateAge(request);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age: " + age));
    }


    private BigDecimal getDefaultAgeCoefficient() {
        return BigDecimal.ONE;
    }

    private Integer calculateAge(TravelCalculatePremiumRequestV1 request) {
        LocalDate personBirthDate = toLocalDate(request.getDateOfBirth());
        LocalDate currentDate = toLocalDate(new Date());
        return Period.between(personBirthDate, currentDate).getYears();
    }
    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

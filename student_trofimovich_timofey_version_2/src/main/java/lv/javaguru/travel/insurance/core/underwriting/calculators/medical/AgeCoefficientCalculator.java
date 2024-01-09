package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AgeCoefficientCalculator {
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    @Value("${age.coefficient.enabled:false}")
    boolean ageCoefficientEnabled;

    BigDecimal getAgeCoefficient(PersonDTO person) {
        return ageCoefficientEnabled ?
                calculateAgeCoefficient(person)
                : getDefaultAgeCoefficient();
    }

    private BigDecimal calculateAgeCoefficient(PersonDTO person) {
        Integer age = calculateAge(person);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age: " + age));
    }


    private BigDecimal getDefaultAgeCoefficient() {
        return BigDecimal.ONE;
    }

    private Integer calculateAge(PersonDTO person) {
        LocalDate personBirthDate = toLocalDate(person.getPersonBirthDate());
        LocalDate currentDate = toLocalDate(new Date());
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

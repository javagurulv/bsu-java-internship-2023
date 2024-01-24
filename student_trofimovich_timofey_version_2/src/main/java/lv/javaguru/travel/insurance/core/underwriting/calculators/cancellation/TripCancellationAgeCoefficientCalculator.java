package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCAgeCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TripCancellationAgeCoefficientCalculator {

    @Autowired
    private TCAgeCoefficientRepository repository;

    public BigDecimal calculateAgeCoefficient(PersonDTO person) {
        Integer age = calculateAge(person);
        return repository.findCoefficient(age)
                .map(TCAgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age: " + age));
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

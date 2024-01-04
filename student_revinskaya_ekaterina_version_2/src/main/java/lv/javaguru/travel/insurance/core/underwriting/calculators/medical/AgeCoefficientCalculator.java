package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.repositories.TMAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AgeCoefficientCalculator {
    @Autowired
    private TMAgeCoefficientRepository TMAgeCoefficientRepository;
    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Value("${age.coefficient.enabled:false}")
    private Boolean ageCoefficientEnabled;

    BigDecimal calculate(PersonDTO person) {
        return ageCoefficientEnabled ? calculateValue(person) : defaultValue();
    }

    private BigDecimal calculateValue(PersonDTO person) {
        Period period = Period.between(localDateFromDate(person.getPersonBirthDate()),
                localDateFromDate(dateTimeUtil.getCurrentDateTime()));
        return TMAgeCoefficientRepository.findByAge(period.getYears())
                .orElseThrow(() -> new RuntimeException("coefficient for person with birthday "
                        + convertDateToString(person.getPersonBirthDate()) + " not found")).getCoefficient();
    }

    private BigDecimal defaultValue() {
        return BigDecimal.ONE;
    }

    private String convertDateToString(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    private LocalDate localDateFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

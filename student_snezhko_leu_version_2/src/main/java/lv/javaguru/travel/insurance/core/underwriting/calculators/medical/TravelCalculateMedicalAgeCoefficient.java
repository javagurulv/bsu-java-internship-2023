package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;


@Component
class TravelCalculateMedicalAgeCoefficient implements TravelRiskPremiumCalculatorMedicalComponent{
    @Autowired
    private AgeCoefficientRepository acRepository;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        try {
            return checkProperty("medical.risk.age.coefficient.enabled")
                    ? acRepository
                        .findCoefficient(
                                calculateAge(
                                        person
                                )
                        )
                        .get()
                        .getCoefficient()
                    : BigDecimal.ONE;
        }
        catch (IOException e) {
            return BigDecimal.ONE;
        }
    }

    public BigDecimal calculatePremium(PersonDTO person) {
        return calculatePremium(null, person);
    }
    private int calculateAge(PersonDTO person) {
        LocalDate personBirthDate = toLocalDate(person.getPersonBirthDate());
        LocalDate now = toLocalDate(dateTimeUtil.getCurrentDateTime());
        int res = Period.between(personBirthDate, now).getYears();
        return res;
    }
    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

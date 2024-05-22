package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TCAgeCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TCAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.CalculateAgeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class TravelCancellationAgeCoefficientTest {
    @InjectMocks
    private TravelAgeCoefficientCancellation calculator;

    @Mock
    private TCAgeCoefficientRepository ageCoefficientRepository;

    @Mock
    private CalculateAgeUtil calculateAgeUtil;

    AgreementDTO agreement;

    PersonDTO person;

    @Test
    public void calculateAgeCoefficientTest() {
        BigDecimal coefficient = BigDecimal.ONE;
        java.util.Date date = new java.util.Date();
        Integer age = 0;
        person = PersonDTOBuilder.createPersonDTO().withBirthDate(new java.util.Date()).build();
        TCAgeCoefficientDomain domain = new TCAgeCoefficientDomain();
        domain.setCoefficient(coefficient);
        when(calculateAgeUtil.calculateAge(date)).thenReturn(age);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(domain));

        assertEquals("", coefficient, calculator.calculatePremium(agreement, person));
    }
}

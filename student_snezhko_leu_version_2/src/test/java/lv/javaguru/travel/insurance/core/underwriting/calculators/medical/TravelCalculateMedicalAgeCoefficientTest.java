package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static lv.javaguru.travel.insurance.core.domain.AgeCoefficientBuilder.createAgeCoefficient;
import static lv.javaguru.travel.insurance.core.validations.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TravelCalculateMedicalAgeCoefficientTest {
    @Mock
    private AgreementDTO agreement;
    @Mock
    private PersonDTO person;
    @Mock
    private AgeCoefficientRepository acRepository;

    @Mock
    private DateTimeUtil dateTimeUtil;
    @InjectMocks
    private TravelCalculateMedicalAgeCoefficient calculator;

    @Test
    public void MedicalAgeCoefficientCalculatorTest() {
        init(15, 1.1);
        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(agreement, person));
    }

    private void init(Integer age, Double ageCoefficient)
    {
        //agreement = mock(AgreementDTO.class);
        //person = mock(PersonDTO.class);

        Date birthDate = new Date();
        Date currentDate = birthDate;

        birthDate.setYear(birthDate.getYear() - age);

        when(person.getPersonBirthDate()).thenReturn(birthDate);
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(new Date());

        AgeCoefficient acValue = mock(AgeCoefficient.class);
        when(acValue.getCoefficient()).thenReturn(BigDecimal.valueOf(ageCoefficient));

        when(acRepository.findCoefficient(age)).thenReturn(Optional.of(acValue));
        when(acRepository.findCoefficient(age + 1)).thenReturn(Optional.of(acValue));
        when(acRepository.findCoefficient(age-1)).thenReturn(Optional.of(acValue));

//        ReflectionTestUtils.setField(calculator, "acRepository", acRepository);
//        ReflectionTestUtils.setField(calculator, "dateTimeUtil", dateTimeUtil);
    }

    @Test
    public void MedicalAgeCoefficientCalculatorIntegrationTest() {
        AgeCoefficient ac = createAgeCoefficient()
                .withAgeFrom(10)
                .withAgeTo(18)
                .withCoefficient(BigDecimal.valueOf(1.1))
                .build();

        PersonDTO personDTO = createPersonDTO().withBirthDate(createDate("2009-03-05")).build();
        AgreementDTO agreementDTO = createAgreementDTO().withPersons(personDTO).build();
        when(acRepository.findCoefficient(14)).thenReturn(Optional.of(ac));
        when(acRepository.findCoefficient(15)).thenReturn(Optional.of(ac));
        when(acRepository.findCoefficient(16)).thenReturn(Optional.of(ac));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(new Date());

        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(agreementDTO, personDTO));
    }
}

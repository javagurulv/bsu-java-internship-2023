package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.medical.TMAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.calculate.medical.TMAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.CalculateAgeUtil;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static lv.javaguru.travel.insurance.core.domain.calculate.builders.AgeCoefficientBuilder.createAgeCoefficient;
import static lv.javaguru.travel.insurance.core.validations.calculate.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TravelCalculateMedicalTMAgeCoefficientTest {
    @InjectMocks
    private TravelCalculateMedicalAgeCoefficient calculator;
    @Mock
    private AgreementDTO agreement;
    @Mock
    private PersonDTO person;
    @Mock
    private TMAgeCoefficientRepository acRepository;


    @Mock
    private CalculateAgeUtil calculateAgeUtil;


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
        when(calculateAgeUtil.calculateAge(birthDate)).thenReturn(age);

        TMAgeCoefficient acValue = mock(TMAgeCoefficient.class);
        when(acValue.getCoefficient()).thenReturn(BigDecimal.valueOf(ageCoefficient));

        when(acRepository.findCoefficient(age)).thenReturn(Optional.of(acValue));
        when(acRepository.findCoefficient(age + 1)).thenReturn(Optional.of(acValue));
        when(acRepository.findCoefficient(age-1)).thenReturn(Optional.of(acValue));

//        ReflectionTestUtils.setField(calculator, "acRepository", acRepository);
//        ReflectionTestUtils.setField(calculator, "dateTimeUtil", dateTimeUtil);
    }

    @Test
    public void MedicalAgeCoefficientCalculatorIntegrationTest() {
        TMAgeCoefficient ac = createAgeCoefficient()
                .withAgeFrom(10)
                .withAgeTo(18)
                .withCoefficient(BigDecimal.valueOf(1.1))
                .build();

        Date date = createDate("2009-03-05");
        Integer age = 15;
        PersonDTO personDTO = createPersonDTO().withBirthDate(createDate("2009-03-05")).build();
        AgreementDTO agreementDTO = createAgreementDTO().withPersons(personDTO).build();
  //      when(acRepository.findCoefficient(14)).thenReturn(Optional.of(ac));
        when(acRepository.findCoefficient(15)).thenReturn(Optional.of(ac));
//        when(acRepository.findCoefficient(16)).thenReturn(Optional.of(ac));
        when(calculateAgeUtil.calculateAge(date)).thenReturn(age);

        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(agreementDTO, personDTO));
    }
}

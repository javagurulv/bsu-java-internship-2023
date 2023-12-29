package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgeCoefficientCalculatorTest {
    @Mock
    AgeCoefficientRepository repository;
    @InjectMocks
    AgeCoefficientCalculator ageCoefficientCalculator;
    @Test
    void shouldNotReturnErrorWhenAgeCoefficientIsNotEnabled() {
        PersonDTO person = mock(PersonDTO.class);
        ReflectionTestUtils.setField(ageCoefficientCalculator, "ageCoefficientEnabled", false);
        BigDecimal ageCoefficient = ageCoefficientCalculator.getAgeCoefficient(person);
        assertThat(ageCoefficient).isEqualTo(BigDecimal.ONE);
        verifyNoInteractions(repository);
    }
    @Test
    void getCorrectAgeCoefficient() {
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);
        int age = 0;
        PersonDTO person = mock(PersonDTO.class);
        ReflectionTestUtils.setField(ageCoefficientCalculator, "ageCoefficientEnabled", true);
        when(person.getPersonBirthDate()).thenReturn(new Date());
        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));
        assertThat(ageCoefficientCalculator.getAgeCoefficient(person)).isEqualTo(expectedCoefficient);
    }
    @Test
    void shouldThrowAnExceptionWhenAgeCoefficientNotFound() {
        int age = 0;
        PersonDTO person = mock(PersonDTO.class);
        ReflectionTestUtils.setField(ageCoefficientCalculator, "ageCoefficientEnabled", true);
        when(person.getPersonBirthDate()).thenReturn(new Date());
        when(repository.findCoefficient(age)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> ageCoefficientCalculator.getAgeCoefficient(person));
        assertThat(exception.getMessage()).isEqualTo("Age coefficient not found for age: " + age);
    }

}

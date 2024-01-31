package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateOfBirthIsInPastValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private DateOfBirthIsInPastValidation validation;
    private PersonDTO person;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
        agreement = mock(AgreementDTO.class);
    }

    @Test
    public void shouldReturnErrorWhenDateOfBirthIsInTheFuture() {
        when(person.getPersonBirthDate()).thenReturn(DateTimeUtil.createDate("20.12.2025"));
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(new ValidationErrorDTO("ERROR_CODE_13", "desc"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person, agreement);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_13");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("desc");
    }

    @Test
    void shouldNotReturnError() {
        when(person.getPersonBirthDate()).thenReturn(DateTimeUtil.createDate("12.03.2020"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person, agreement);
        assertThat(ValidationErrorDTO).isEmpty();
    }
    
}

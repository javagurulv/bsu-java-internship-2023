package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateToValidatonTest {
    @Mock
    ValidationErrorFactory factory;
    @InjectMocks
    private DateToValidation validation;


    @Test
    void shouldReturnErrorWhenAgreementDateFromIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateTo()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_4")).thenReturn(new ValidationErrorDTO("ERROR_CODE_4","Date to field must not be empty!"));
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_4");
        assertThat(validationError.get().getDescription()).isEqualTo("Date to field must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateTo()).thenReturn(new Date());
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isEmpty();
    }
}



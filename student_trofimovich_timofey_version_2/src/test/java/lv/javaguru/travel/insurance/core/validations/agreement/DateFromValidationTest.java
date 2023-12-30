package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
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

public class DateFromValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private DateFromValidation validation;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldReturnErrorWhenAgreementDateFromIsEmpty() {
        when(agreement.getAgreementDateFrom()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_3")).thenReturn(new ValidationErrorDTO("ERROR_CODE_3", "Date from field must not be empty!"));
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_3");
        assertThat(validationError.get().getDescription()).isEqualTo("Date from field must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        when(agreement.getAgreementDateFrom()).thenReturn(new Date());
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isEmpty();
    }
}



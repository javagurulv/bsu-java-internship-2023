package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
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
public class FirstNameValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private FirstNameValidation validation;

    @Test
    void shouldReturnErrorWhenFirstNameIsNull() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1", "First name must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(validationError.get().getDescription()).isEqualTo("First name must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenFirstNameIsEmpty() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(factory.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1", "First name must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(validationError.get().getDescription()).isEqualTo("First name must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("first name");
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
    }
}

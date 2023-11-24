package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
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
    ValidationErrorFactory factory;
    @InjectMocks
   private DateFromValidation validation;

    @Test
    void shouldReturnErrorWhenAgreementDateFromIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_3")).thenReturn(new ValidationError("ERROR_CODE_3", "Date from field must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_3");
        assertThat(validationError.get().getDescription()).isEqualTo("Date from field must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
    }
}



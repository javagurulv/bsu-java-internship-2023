package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateFromValidationTest {
    DateFromValidation validation = new DateFromValidation();

    @Test
    void shouldReturnErrorWhenAgreementDateFromIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getField()).isEqualTo("agreementDateFrom");
        assertThat(validationError.get().getMessage()).isEqualTo("Must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
    }
}


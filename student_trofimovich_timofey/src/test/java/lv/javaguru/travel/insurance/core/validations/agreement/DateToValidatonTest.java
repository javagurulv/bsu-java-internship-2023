package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
public class DateToValidatonTest {
    @Mock
    ValidationErrorFactory factory;
    @InjectMocks
    private DateToValidation validation;


    @Test
    void shouldReturnErrorWhenAgreementDateFromIsEmpty() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_4")).thenReturn(new ValidationError("ERROR_CODE_4","Date to field must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_4");
        assertThat(validationError.get().getDescription()).isEqualTo("Date to field must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
    }
}



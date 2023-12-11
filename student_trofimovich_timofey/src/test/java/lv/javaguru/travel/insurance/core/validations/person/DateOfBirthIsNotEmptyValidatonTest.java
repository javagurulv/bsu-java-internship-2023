package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
public class DateOfBirthIsNotEmptyValidatonTest {
    @Mock
    ValidationErrorFactory errorFactory;
    @InjectMocks
    DateOfBirthIsNotEmptyValidaton validaton;

    @Test
    void shouldReturnErrorWhenDateOfBirthIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getDateOfBirth()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_12")).thenReturn(new ValidationError("ERROR_CODE_12", "desc"));
        Optional<ValidationError> validationError = validaton.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_12");
        assertThat(validationError.get().getDescription()).isEqualTo("desc");
    }


    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getDateOfBirth()).thenReturn(new Date());
        Optional<ValidationError> validationError = validaton.validate(request);
        assertThat(validationError).isEmpty();
    }

}

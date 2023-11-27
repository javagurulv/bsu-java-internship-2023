package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePersonLastNameTest {
    private TravelRequestValidation validatePersonLastName;
    @BeforeEach void setup(){
        validatePersonLastName = new ValidatePersonLastName();
    }

    @Test
    void testCorrectValidatePersonLastNameIfItIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        ValidationError correctError = new ValidationError("personLastName", "Must not be empty!");

        Optional<ValidationError> validationErrorOptional = validatePersonLastName.execute(request);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidatePersonLastNameIfItIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        ValidationError correctError = new ValidationError("personLastName", "Must not be empty!");

        Optional<ValidationError> validationErrorOptional = validatePersonLastName.execute(request);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidatePersonLastNameIfFieldIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("lastName");

        Optional<ValidationError> validationErrorOptional = validatePersonLastName.execute(request);
        assertThat(validationErrorOptional.isEmpty()).isTrue();
    }
}

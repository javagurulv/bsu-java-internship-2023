package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePersonFirstNameTest {
    private TravelRequestValidation validatePersonFirstName;
    @BeforeEach
    void setup(){
        validatePersonFirstName = new ValidatePersonFirstName();
    }
    @Test
    void testCorrectValidatePersonFirstNameIfItIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        ValidationError correctError = new ValidationError("personFirstName", "Must not be empty!");

        Optional<ValidationError> validationErrorOptional = validatePersonFirstName.execute(request);
        System.out.println(validationErrorOptional);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidatePersonFirstNameIfItIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError correctError = new ValidationError("personFirstName", "Must not be empty!");

        Optional<ValidationError> validationErrorOptional = validatePersonFirstName.execute(request);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidatePersonFirstNameIfFieldISCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");

        Optional<ValidationError> validationErrorOptional = validatePersonFirstName.execute(request);
        assertThat(validationErrorOptional.isEmpty()).isTrue();
    }
}

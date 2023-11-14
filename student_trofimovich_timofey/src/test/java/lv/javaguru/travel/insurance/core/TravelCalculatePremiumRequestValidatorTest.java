package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();


    @Test
    void shouldReturnErrorWhenFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("personFirstName"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }

    @Test
    void shouldReturnErrorWhenFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("personFirstName"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }
    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isEqualTo(0)
        );
    }
}

package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.PersonFirstNameValidator;
import lv.javaguru.travel.insurance.core.validations.PersonLastNameValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonLastNameValidationTest {

    @Mock private ValidationErrorFactory factory;

    @InjectMocks
    private PersonLastNameValidator validator;

    @Test
    public void shouldReturnErrorIfPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(factory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(factory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorIfPersonLastNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Vorobey");
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factory);
    }
}

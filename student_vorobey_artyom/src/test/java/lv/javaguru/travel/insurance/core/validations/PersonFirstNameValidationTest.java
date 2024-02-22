package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.PersonFirstNameValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidationTest {

    @Mock private ValidationErrorFactory factory;

    @InjectMocks
    private PersonFirstNameValidator validator;

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(factory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(factory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorIfPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Artyom");
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factory);
    }
}

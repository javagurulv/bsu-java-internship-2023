package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidatePersonFirstNameTest {
    @InjectMocks
    private ValidatePersonFirstName validator = new ValidatePersonFirstName();
    @Mock
    private ValidationErrorFactory factory;

    @Test
    public void firstNameShouldNotBeNull() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationErrorMock = mock(ValidationError.class);
        when(requestMock.getPersonFirstName()).thenReturn(null);
        when(factory.createError("ERROR_CODE_6")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void firstNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationErrorMock = mock(ValidationError.class);
        when(requestMock.getPersonFirstName()).thenReturn("");
        when(factory.createError("ERROR_CODE_6")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        when(requestMock.getPersonFirstName()).thenReturn("Vasya");
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factory);
    }
}

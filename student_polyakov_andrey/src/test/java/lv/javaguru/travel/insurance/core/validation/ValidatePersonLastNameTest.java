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
public class ValidatePersonLastNameTest {
    @InjectMocks
    private ValidatePersonLastName validator = new ValidatePersonLastName();
    @Mock
    private ValidationErrorFactory factory;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidationError validationErrorMock;

    @Test
    public void lastNameShouldNotBeNull() {
        when(requestMock.getPersonLastName()).thenReturn(null);
        when(factory.createError("ERROR_CODE_7")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void lastNameShouldNotBeEmpty() {
        when(requestMock.getPersonLastName()).thenReturn("");
        when(factory.createError("ERROR_CODE_7")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        when(requestMock.getPersonLastName()).thenReturn("Vasya");
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factory, validationErrorMock);
    }
}

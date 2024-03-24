package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateIfPersonBirthDateEmptyTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private TravelCalculatePremiumRequest request;
    @InjectMocks
    private ValidateIfPersonBirthDateEmpty validateIfPersonBirthDateEmpty;

    @Test
    public void shouldReturnNoErrorWhenPersonBirthDateIsPresent() {
        when(request.getPersonBirthDate()).thenReturn(new Date());
        Optional<ValidationError> errorOptional = validateIfPersonBirthDateEmpty.validate(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPersonBirthDateIsNull() {
        when(request.getPersonBirthDate()).thenReturn(null);
        when(validationErrorFactory.createError("ERROR_CODE_11"))
                .thenReturn(new ValidationError("ERROR_CODE_11",
                        "Person Birth Date must be provided when TRAVEL_MEDICAL is selected"));

        Optional<ValidationError> errorOptional = validateIfPersonBirthDateEmpty.validate(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("ERROR_CODE_11", errorOptional.get().getErrorCode());
        assertEquals("Person Birth Date must be provided when TRAVEL_MEDICAL is selected", errorOptional.get().getDescription());
    }
}

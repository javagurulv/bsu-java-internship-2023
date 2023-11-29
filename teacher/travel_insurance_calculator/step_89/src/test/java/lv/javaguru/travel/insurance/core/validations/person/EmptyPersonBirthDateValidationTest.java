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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyPersonBirthDateValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyPersonBirthDateValidation validation;

    @Test
    public void shouldReturnNoErrorWhenPersonBirthDateIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonBirthDate()).thenReturn(new Date());
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPersonBirthDateIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonBirthDate()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_11"))
                .thenReturn(new ValidationError("ERROR_CODE_11", "Person Birth Date must be provided when TRAVEL_MEDICAL is selected"));
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_11", errorOpt.get().getErrorCode());
        assertEquals("Person Birth Date must be provided when TRAVEL_MEDICAL is selected", errorOpt.get().getDescription());
    }

}
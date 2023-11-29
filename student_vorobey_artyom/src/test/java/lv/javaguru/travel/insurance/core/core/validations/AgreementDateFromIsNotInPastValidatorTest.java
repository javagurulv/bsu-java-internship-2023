package lv.javaguru.travel.insurance.core.core.validations;

import lv.javaguru.travel.insurance.core.validations.AgreementDateFromIsNotInPastValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.DateFunctions.createDateFromString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class AgreementDateFromIsNotInPastValidatorTest {
    AgreementDateFromIsNotInPastValidator validator = new AgreementDateFromIsNotInPastValidator();

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("24.11.2023"));
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "agreementDateFrom");
        assertEquals(errorOptional.get().getMessage(), "Must not be in past!");
    }
}

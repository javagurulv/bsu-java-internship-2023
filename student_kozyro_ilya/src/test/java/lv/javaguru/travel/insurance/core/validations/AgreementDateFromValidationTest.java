package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromValidationTest {
    @Mock
    private DateService dateService;

    @InjectMocks
    private AgreementDateFromValidation validation;


    @Test
    void dontHaveMandatoryDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(null);

        Optional<ValidationError> error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Shouldn't be empty!", error.get().getError());
    }

    @Test
    void haveMandatoryField() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isEmpty());
    }


}

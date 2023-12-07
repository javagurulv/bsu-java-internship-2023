package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.util.ErrorFileLoaderUtil;
import lv.javaguru.travel.insurance.dto.Placer;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.MISSING_MANDATORY_FIELD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromValidationTest {
    @Mock
    private DateService dateService;


    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private AgreementDateFromValidation validation;


    @Test
    void dontHaveMandatoryDateFrom() {

        //Optional<ValidationError> error = validation.execute(request);

        //System.out.println(error.get());
        //assertFalse(error.isEmpty());
        //assertEquals(MISSING_MANDATORY_FIELD, error.get().getErrorCode());
        //assertEquals("missing agreementDateFrom", error.get().getErrorDescription());
    }

    @Test
    void haveMandatoryField() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isEmpty());
    }


}

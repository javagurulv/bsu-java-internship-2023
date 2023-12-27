package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateServiceUtil;
import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.MANDATORY_FIELD_MISSING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromValidationTest {
    @Mock
    private DateServiceUtil dateService;

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private AgreementDateFromValidation validation;


    @Test
    void dontHaveMandatoryFieldDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(null);
        when(validationErrorFactory.buildError(eq(MANDATORY_FIELD_MISSING), argThat(isPlaceHolder())))
                .thenReturn(new ValidationError(MANDATORY_FIELD_MISSING, "description"));

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isPresent());
        assertEquals(new ValidationError(MANDATORY_FIELD_MISSING, "description"), error.get());
    }

    @Test
    void haveMandatoryField() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isEmpty());
    }

    private ArgumentMatcher<Placeholder> isPlaceHolder() {
        return argument -> argument.getKey().equals("fieldName") && argument.getValue().equals("agreementDateFrom");
    }

}

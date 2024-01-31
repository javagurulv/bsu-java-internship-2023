package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class DateToValidateTest {

    @Autowired private CreateDate createDate;
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks DateToValidate validate;

    @Test
    void validatorWhenDateToIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_5")).thenReturn(validationError);
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
         }

    @Test
    void validatorWhenDateToInFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate.createDate("10-10-2030"));
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isPresent());
        assertEquals(request.getAgreementDateTo(), createDate.createDate("10-10-2030"));
    }
}
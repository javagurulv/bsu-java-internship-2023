package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class DateFromValidateTest {

    @Autowired private CreateDate createDate;

    private DateFromValidate validate = new DateFromValidate();

    @Test
    void validatorWhenDateFromIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "agreementDateFrom");
        assertEquals(errors.get().getMessage(), "Must not be empty!");
    }

    @Test
    void validatorWhenDateFromInFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate.createDate("10-10-2030"));
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isPresent());
        assertEquals(request.getAgreementDateFrom(), createDate.createDate("10-10-2030"));
    }
}
package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DateFromInThePastValidateTest {

    @Mock private DateTimeService dateTimeService;
    @Mock private ValidationErrorFactory errorFactory;
    @InjectMocks DateFromInThePastValidate validate;

    @Autowired CreateDate createDate;

    @Test
    void validationWhenDateFromInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate.createDate("10-10-2020"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate.createDate("10-10-2023"));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        //assertEquals(errors.get().getDescription(), validate.errorCode1Message);
        assertSame(errors.get(), validationError);
    }

    @Test
    void validationWhenDateFromInFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate.createDate("10-10-2027"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate.createDate("10-10-2023"));
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isPresent());
    }
}
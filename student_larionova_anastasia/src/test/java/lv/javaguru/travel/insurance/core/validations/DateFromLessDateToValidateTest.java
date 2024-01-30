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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DateFromLessDateToValidateTest {

    @Autowired CreateDate createDate;

    @InjectMocks private DateFromLessDateToValidate validation;

    @Mock private DateTimeService dateTimeService;

    @Test
    void validationWhenDateFromLessDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate.createDate("10-10-2027"));
        when(request.getAgreementDateTo()).thenReturn(createDate.createDate("10-10-2026"));
        Optional<ValidationError> errors = validation.validator(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_2");
        assertEquals(errors.get().getDescription(), validation.errorCode2Message);
    }
}
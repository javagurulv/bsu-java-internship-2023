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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DateFromInThePastValidateTest {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    DateFromInThePastValidate validate;

    @Autowired CreateDate createDate;

    @Test
    void validationWhenDateFromInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate.createDate("10-10-2020"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate.createDate("10-10-2023"));
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "agreementDateFrom");
        assertEquals(errors.get().getMessage(), "AgreementDateFrom should only be from the future");
    }
}
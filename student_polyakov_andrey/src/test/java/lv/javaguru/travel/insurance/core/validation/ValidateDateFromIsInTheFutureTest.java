package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class ValidateDateFromIsInTheFutureTest {
    @InjectMocks
    private ValidateDateFromIsInTheFuture validator = new ValidateDateFromIsInTheFuture();
    @Mock
    private ValidationErrorFactory factory;
    @Mock
    private DateTimeUtil dateTimeUtil;

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromInThePast() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationErrorMock = mock(ValidationError.class);
        when(requestMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2007"));
        when(dateTimeUtil.getTodaysDateAndTime()).thenReturn(createDate("01.01.2024"));
        when(factory.createError("ERROR_CODE_3")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromInFuture() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        when(requestMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2027"));
        when(dateTimeUtil.getTodaysDateAndTime()).thenReturn(createDate("01.01.2024"));
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factory);
    }
}

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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateIfPersonBirthDateIsInThePastTest {
    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private TravelCalculatePremiumRequest request;
    @Mock
    private ValidationError validationError;
    @InjectMocks
    private ValidatePersonBirthDateIsInThePast validatePersonBirthDateIsInThePast;

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void shouldReturnErrorWhenPersonBirthDateInTheFuture() {
        when(request.getPersonBirthDate()).thenReturn(createDate("01.01.2030"));
        when(dateTimeUtil.getTodaysDateAndTime()).thenReturn(createDate("01.01.2024"));

        when(validationErrorFactory.createError("ERROR_CODE_12")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validatePersonBirthDateIsInThePast.validate(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonBirthDateDateInThePast() {
        when(request.getPersonBirthDate()).thenReturn(createDate("01.01.2020"));
        when(dateTimeUtil.getTodaysDateAndTime()).thenReturn(createDate("01.01.2024"));

        Optional<ValidationError> errorOptional = validatePersonBirthDateIsInThePast.validate(request);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(validationErrorFactory, validationError);
    }
}

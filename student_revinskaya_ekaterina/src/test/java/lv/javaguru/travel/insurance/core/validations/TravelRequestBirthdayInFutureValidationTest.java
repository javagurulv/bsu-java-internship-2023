package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.expression.spel.ast.OpAnd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestBirthdayInFutureValidationTest {
    @InjectMocks
    TravelRequestBirthdayInFutureValidation birthdayInFutureValidation;
    @Mock
    DateTimeUtil dateTimeUtil;
    @Mock
    TravelCalculatePremiumRequest request;
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainsErrorBirthdayInFutureTest(){
        ValidationError validationError = mock(ValidationError.class);
        when(request.getBirthday()).thenReturn(createDate("2024.04.08"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("2024.01.08"));
        when(validationErrorFactory.buildError("ERROR_CODE_13")).thenReturn(validationError);
        Optional<ValidationError> error= birthdayInFutureValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

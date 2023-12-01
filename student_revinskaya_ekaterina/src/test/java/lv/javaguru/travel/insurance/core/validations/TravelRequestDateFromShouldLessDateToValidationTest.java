package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestDateFromShouldLessDateToValidationTest {
    @InjectMocks
    private TravelRequestDateFromLessDateToValidation dateFromLessDateToValidation;

    @Mock
    private TravelCalculatePremiumRequestV1 request;
    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Test
    public void responseShouldContainErrorDateFromMoreToTest() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2023"));
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_7")).thenReturn(validationError);
        Optional<ValidationError> error = dateFromLessDateToValidation.validate(request);
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

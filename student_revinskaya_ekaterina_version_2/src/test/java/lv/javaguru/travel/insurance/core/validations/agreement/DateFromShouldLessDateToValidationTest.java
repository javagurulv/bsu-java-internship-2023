package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
public class DateFromShouldLessDateToValidationTest {
    @InjectMocks
    private DateFromLessDateToValidation dateFromLessDateToValidation;

    @Mock
    private AgreementDTO request;
    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Test
    public void responseShouldContainErrorDateFromMoreToTest() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2023"));
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_7")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = dateFromLessDateToValidation.validate(request);
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

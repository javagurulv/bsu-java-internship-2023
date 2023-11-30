package lv.javaguru.travel.insurance.core.validations;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateToIsInFutureValidationTest {
    @Mock
    ValidationErrorFactory factory;
    @InjectMocks
    private DateToIsInFutureValidation validation;

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("20.12.2020"));
        when(factory.buildError("ERROR_CODE_6")).thenReturn(new ValidationError("ERROR_CODE_6", "Date to must be in the future!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_6");
        assertThat(validationError.get().getDescription()).isEqualTo("Date to must be in the future!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("12.03.2025"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
    }

    private Date createDate(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

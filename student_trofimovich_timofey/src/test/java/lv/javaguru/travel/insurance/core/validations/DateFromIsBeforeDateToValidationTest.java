package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateFromIsBeforeDateToValidationTest {
    private DateFromIsBeforeDateToValidation validation = new DateFromIsBeforeDateToValidation();

    @Test
    void shouldReturnErrorWhenAgreementDateToIsBeforeAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("20.12.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("19.12.2025"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getField()).isEqualTo("agreementDateTo");
        assertThat(validationError.get().getMessage()).isEqualTo("Must not be before agreementDateFrom!");

    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.03.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("13.03.2025"));
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

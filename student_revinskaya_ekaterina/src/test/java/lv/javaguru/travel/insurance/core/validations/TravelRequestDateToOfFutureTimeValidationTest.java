package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TravelRequestDateToOfFutureTimeValidationTest {
    @InjectMocks
    private TravelRequestAgreementDateToOfFutureValidation dateToValidation;

    @Mock private DateTimeService dateTimeService;

    @Test
    public void responseShouldContainDateToOfFutureTimeTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("8.07.2023"));
        Optional<ValidationError> error= dateToValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "agreementDateTo must be future date");
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
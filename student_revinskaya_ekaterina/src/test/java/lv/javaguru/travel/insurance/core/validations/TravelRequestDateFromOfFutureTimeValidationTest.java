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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TravelRequestDateFromOfFutureTimeValidationTest {
    @InjectMocks
    TravelRequestAgreementDateFromOfFutureValidation dateFromValidation = new TravelRequestAgreementDateFromOfFutureValidation();
    @Mock
    DateTimeService dateTimeService1;
    @Test
    public void responseShouldContainDateFromOfFutureTimeTest() {

        when(dateTimeService1.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        dateFromValidation.dateTimeService = dateTimeService1;
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2023"));
        Optional<ValidationError> error= dateFromValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateFrom");
        assertEquals(error.get().getMessage(), "agreementDateFrom must be future date");
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

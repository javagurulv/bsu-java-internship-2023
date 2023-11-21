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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestDateFromShouldLessDateToValidationTest {
@InjectMocks
   private TravelRequestDateFromLessDateToValidation dateFromLessDateToValidation = new TravelRequestDateFromLessDateToValidation();

    @Mock
    TravelCalculatePremiumRequest request;
    @Test
    public void responseShouldContainErrorDateFromMoreToTest() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2023"));
        Optional<ValidationError> error= dateFromLessDateToValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(),"agreementDateFrom" );
        assertEquals(error.get().getMessage(),"agreementDateFrom must be less than agreementDateTo!" );

    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

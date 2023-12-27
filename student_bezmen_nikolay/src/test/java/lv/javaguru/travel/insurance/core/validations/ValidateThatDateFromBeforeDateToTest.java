package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ValidateThatDateFromBeforeDateToTest {
    private TravelRequestValidation validateThatDateFromBeforeDateTo;

    @BeforeEach
    void setup(){
        validateThatDateFromBeforeDateTo = new ValidateThatDateFromBeforeDateTo();
    }
    @Test
    void testCorrectValidateThatDateFromBeforeDateTo(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2100-11-11"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2100-11-10"));
        ValidationError correctError = new ValidationError("agreementDateFrom, agreementDateTo",
                "dateFrom must be before dateTo");
        Optional<ValidationError> validationErrorOptional = validateThatDateFromBeforeDateTo.execute(request);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidateThatDateFromBeforeDateToIfFieldsAreCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2100-11-11"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2100-11-18"));

        Optional<ValidationError> validationErrorOptional = validateThatDateFromBeforeDateTo.execute(request);
        assertThat(validationErrorOptional.isEmpty()).isTrue();
    }
    private Date createDate(String dateForParse){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateForParse);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

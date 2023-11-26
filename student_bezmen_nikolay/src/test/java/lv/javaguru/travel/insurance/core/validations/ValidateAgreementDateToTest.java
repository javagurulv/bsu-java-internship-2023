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

public class ValidateAgreementDateToTest {
    private TravelRequestValidation validateAgreementDateTo;
    @BeforeEach
    void setup(){
        validateAgreementDateTo = new ValidateAgreementDateTo();
    }
    @Test
    void testCorrectValidateAgreementDateTo(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        ValidationError correctError = new ValidationError("agreementDateTo", "Must not be empty!");

        Optional<ValidationError> validationErrorOptional = validateAgreementDateTo.execute(request);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidateAgreementDateToIfFieldIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("2100-10-12"));

        Optional<ValidationError> validationErrorOptional = validateAgreementDateTo.execute(request);
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

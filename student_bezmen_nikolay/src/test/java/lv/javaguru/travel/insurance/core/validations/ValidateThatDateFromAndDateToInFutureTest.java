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

public class ValidateThatDateFromAndDateToInFutureTest {
    private TravelRequestValidation validateThatDateFromAndDateToInFuture;
    @BeforeEach
    void setup(){
        validateThatDateFromAndDateToInFuture = new ValidateThatDateFromAndDateToInFuture();
    }
    @Test
    void testCorrectValidateThatDateFromAndDateToInFuture(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2022-11-11"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2022-11-18"));
        ValidationError correctError = new ValidationError("agreementDateTo",
                "dateTo should not be from the past");

        Optional<ValidationError> validationErrorOptional = validateThatDateFromAndDateToInFuture.execute(request);
        assertThat(validationErrorOptional.isPresent()).isTrue();

        ValidationError validationError = validationErrorOptional.get();

        assertThat(validationError.getField()).isEqualTo(correctError.getField());
        assertThat(validationError.getMessage()).isEqualTo(correctError.getMessage());
    }

    @Test
    void testCorrectValidateThatDateFromAndDateToInFutureIfFieldsAreCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2050-11-11"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2100-11-18"));

        Optional<ValidationError> validationErrorOptional = validateThatDateFromAndDateToInFuture.execute(request);
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

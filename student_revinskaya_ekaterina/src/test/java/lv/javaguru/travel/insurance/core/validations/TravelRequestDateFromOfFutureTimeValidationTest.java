package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.ErrorCodesPropertiesReader;
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
    private TravelRequestAgreementDateFromOfFutureValidation dateFromValidation = new TravelRequestAgreementDateFromOfFutureValidation();
    @Mock
    private DateTimeService dateTimeService1;
    @Mock
    private ErrorCodesPropertiesReader errorCodesPropertiesReader;
    @Test
    public void responseShouldContainDateFromOfFutureTimeTest() {

        when(dateTimeService1.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2023"));
        when(errorCodesPropertiesReader.getDescription("ERROR_CODE_5")).thenReturn("error Description");
        Optional<ValidationError> error= dateFromValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_5");
        assertEquals(error.get().getDescription(), "error Description");
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

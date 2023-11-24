package lv.javaguru.travel.insurance.core.validator.validation;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.travel.AgreementDateFromValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromValidationTest {
    @Mock
    private DateService dateService;

    @InjectMocks
    private AgreementDateFromValidation validation;


    @Test
    void dontHaveMandatoryDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(null);

        Optional<ValidationError> error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Shouldn't be empty!", error.get().getError());
    }

    @Test
    void haveMandatoryField() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var date = createDate("11.11.2011");
        when(request.getAgreementDateFrom()).thenReturn(date);

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isEmpty());
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

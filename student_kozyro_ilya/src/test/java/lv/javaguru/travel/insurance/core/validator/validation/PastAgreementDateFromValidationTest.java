package lv.javaguru.travel.insurance.core.validator.validation;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.travel.PastAgreementDateFromValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PastAgreementDateFromValidationTest {

    @Mock
    DateService dateService;

    @InjectMocks
    PastAgreementDateFromValidation validation;


    @Test
    void inPastDate() throws ParseException {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var todayDateStr = "15.10.2000";
        var todayDate = createDate(todayDateStr);

        var dateStr = "10.10.2000";
        var date = createDate(dateStr);

        when(dateService.getTodayDate()).thenReturn(createDate(todayDateStr));

        when(request.getAgreementDateFrom()).thenReturn(date);

        var error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Should be in a future, not in a past!", error.get().getError());
    }

    @Test
    void okDate() throws ParseException {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var todayDateStr = "15.10.2000";
        var todayDate = createDate(todayDateStr);

        var dateStr = "21.10.2000";
        var date = createDate(dateStr);

        when(dateService.getTodayDate()).thenReturn(createDate(todayDateStr));

        when(request.getAgreementDateFrom()).thenReturn(date);

        var error = validation.execute(request);

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

package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateCorrectOrderTest {


    @InjectMocks
    DateCorrectOrderValidation validation;

    @Test
    void testBadOrder() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var date1Str = "21.10.2000";
        var date1 = createDate(date1Str);

        var date2Str = "10.10.2000";
        var date2 = createDate(date2Str);

        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);

        var error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("agreementDayFrom", error.get().getField());
        assertEquals("Must be before agreementDayTo", error.get().getError());
    }

    @Test
    void testGoodOrder() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var date1Str = "10.10.2000";
        var date1 = createDate(date1Str);

        var date2Str = "21.10.2000";
        var date2 = createDate(date2Str);

        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);

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

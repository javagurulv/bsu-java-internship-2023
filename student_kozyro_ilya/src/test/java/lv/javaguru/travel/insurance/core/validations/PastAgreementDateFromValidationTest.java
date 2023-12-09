package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateServiceUtil;
import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.PAST_DATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PastAgreementDateFromValidationTest {

    @Mock
    DateServiceUtil dateService;

    @Mock
    ValidationErrorFactory validationErrorFactory;

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

        when(validationErrorFactory.buildError(eq(PAST_DATE), argThat(isPlaceHolder())))
                .thenReturn(new ValidationError(PAST_DATE, "description"));


        var error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals(PAST_DATE, error.get().getErrorCode());
        assertEquals("description", error.get().getErrorDescription());
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

    private ArgumentMatcher<Placeholder> isPlaceHolder() {
        return argument -> argument.getKey().equals("fieldName") && argument.getValue().equals("agreementDateFrom");
    }
}

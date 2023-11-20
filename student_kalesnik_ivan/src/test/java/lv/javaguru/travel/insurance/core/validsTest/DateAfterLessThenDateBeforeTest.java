package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.core.valids.DateAfterLessThenDateBefore;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DateAfterLessThenDateBeforeTest {

    /*private DateAfterLessThenDateBefore validation;

    @BeforeEach
    public void setUp() {
        validation = new DateAfterLessThenDateBefore();
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualToDateTo() {
        Date date = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(date);
        request.setAgreementDateTo(date);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must be less then agreementDateTo!", result.get().getDescription());
        assertEquals("agreementDateFrom", result.get().getErrorCode());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        Date dateFrom = new Date(System.currentTimeMillis() + 10000);
        Date dateTo = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must be less then agreementDateTo!", result.get().getDescription());
        assertEquals("agreementDateFrom", result.get().getErrorCode());
    }

    @Test
    public void shouldReturnEmptyWhenDateFromIsBeforeDateTo() {
        Date dateFrom = new Date();
        Date dateTo = new Date(System.currentTimeMillis() + 10000);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }*/

    @Mock
    private ErrorCodeValueUtil errorCodeUtil;

    @InjectMocks
    private DateAfterLessThenDateBefore validation;

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_5")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getErrorCode(), "ERROR_CODE_5");
        assertEquals(errorOpt.get().getDescription(), "error description");
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_5")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getErrorCode(), "ERROR_CODE_5");
        assertEquals(errorOpt.get().getDescription(), "error description");
    }

    @Test
    public void shouldNotReturnErrorWhenDateFromIsLessDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2025"));
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorCodeUtil);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.core.valids.AgreementDateToInFutureValidation;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgreementDateToInFutureValidationTest {

    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private ErrorCodeValueUtil errorCodeUtil;

    @InjectMocks
    private AgreementDateToInFutureValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateToInThePast() {

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2020"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_3")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getErrorCode(), "ERROR_CODE_3");
        assertEquals(errorOpt.get().getDescription(), "error description");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToInTheFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
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
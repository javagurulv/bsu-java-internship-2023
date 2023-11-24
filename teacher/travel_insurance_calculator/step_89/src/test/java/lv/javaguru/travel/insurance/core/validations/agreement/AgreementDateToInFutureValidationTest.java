package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgreementDateToInFutureValidationTest {

    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateToInFutureValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateToInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2020"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_3")).thenReturn(validationError);
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToInTheFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
class AgreementDateFromValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateFromValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateFrom()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
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
package lv.javaguru.travel.insurance.core.validation;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateAgreementDateToTest {
    @Mock
    private ValidationErrorFactory factoryMock;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidationError validationErrorMock;
    @InjectMocks
    private ValidateAgreementDateTo validator = new ValidateAgreementDateTo();

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void agreementDateToShouldNotBeNull() {
        when(requestMock.getAgreementDateTo()).thenReturn(null);
        when(factoryMock.createError("ERROR_CODE_2")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToIsPresent() {
        when(requestMock.getAgreementDateTo()).thenReturn(createDate("02.01.2027"));
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factoryMock, validationErrorMock);
    }
}

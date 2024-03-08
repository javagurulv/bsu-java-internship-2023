package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {
    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @InjectMocks
    private  ValidationErrorFactory factory;
    @Test
    public void correctValidationErrorCreationTest() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE")).thenReturn("error description");
        ValidationError error = factory.createError("ERROR_CODE");
        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getDescription(), "error description");
    }
}

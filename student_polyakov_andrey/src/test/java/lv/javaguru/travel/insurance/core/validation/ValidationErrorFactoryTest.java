package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {
    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @InjectMocks
    private  ValidationErrorFactory factory;
    @Test
    public void createErrorCasualMethodTest() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE")).thenReturn("error description");
        ValidationError validationError = factory.createError("ERROR_CODE");
        assertEquals(validationError.getErrorCode(), "ERROR_CODE");
        assertEquals(validationError.getDescription(), "error description");
    }
    @Test
    public void createErrorMethodWithPlaceholderTest() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER_1", "ERROR_1");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder))).thenReturn("error ERROR_1 description");
        ValidationError validationError = factory.createError("ERROR_CODE", List.of(placeholder));
        assertEquals(validationError.getErrorCode(), "ERROR_CODE");
        assertEquals(validationError.getDescription(), "error ERROR_1 description");
    }
}

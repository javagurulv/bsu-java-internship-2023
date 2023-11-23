package lv.javaguru.travel.insurance.core.validations;

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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {
@InjectMocks
    ValidationErrorFactory validationErrorFactory;
    @Mock
    ErrorCodeUtil reader;
    @Test
    public void buildErrorByCodeTest() {
        when(reader.getErrorDescription("ERROR_CODE")).thenReturn("description");
        ValidationError error = validationErrorFactory.buildError("ERROR_CODE");
        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getDescription(), "description");
    }
    @Test
    public void buildErrorByCodeAndListPlaceholderTest() {
        Placeholder placeholder = new Placeholder("name", "value");
        when(reader.getErrorDescription("ERROR_CODE", List.of(placeholder))).thenReturn("value description");
        ValidationError error = validationErrorFactory.buildError("ERROR_CODE", List.of(placeholder));
        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getDescription(), "value description");
    }


}


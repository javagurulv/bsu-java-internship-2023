package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class ValidationErrorFactoryTest {
    @InjectMocks
    private ValidationErrorFactory errorFactory;

    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @Test
    public void simpleErrorCodeTest() {
        String errorCode = "TEST_ERROR_CODE";
        String description = "Description for test error code";
        when(errorCodeUtil.getErrorDescription(errorCode)).thenReturn(description);
        ReflectionTestUtils.setField(errorFactory, "util", errorCodeUtil);
        ValidationErrorDTO error = errorFactory.buildError(errorCode);
        assertEquals("",errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
    }

    @Test
    public void errorCodeWithPlaceholderTest() {
        String placeholderName = "PLACEHOLDER";
        String placeholderValue = "placeholder_value";
        String errorCode = "TEST_ERROR_CODE";
        String description = "Description with placeholder " + placeholderValue;

        List<Placeholder> placeholders = List.of(new Placeholder(placeholderName, placeholderValue));
        when(errorCodeUtil.getErrorDescription(errorCode, placeholders))
                .thenReturn(description);
        ReflectionTestUtils.setField(errorFactory, "util", errorCodeUtil);

        ValidationErrorDTO error = errorFactory.buildError(errorCode, placeholders);
        assertEquals("",errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
    }
}

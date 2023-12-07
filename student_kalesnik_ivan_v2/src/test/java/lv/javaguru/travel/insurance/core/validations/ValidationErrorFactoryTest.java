package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.util.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ValidationErrorFactoryTest {

    @InjectMocks
    private ValidationErrorFactory errorFactory;

    @Mock
    private ErrorCodeValueUtil errorCodeUtil;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldBuildErrorWithErrorCode() {
        String errorCode = "ERROR_CODE_1";
        String errorDescription = "Error description";
        when(errorCodeUtil.getErrorDescription(errorCode)).thenReturn(errorDescription);

        ValidationErrorDto errorDto = errorFactory.buildError(errorCode);

        assertEquals(errorCode, errorDto.getErrorCode());
        assertEquals(errorDescription, errorDto.getDescription());
    }

    @Test
    void shouldBuildErrorWithErrorCodeAndPlaceholders() {
        String errorCode = "ERROR_CODE_2";
        String errorDescription = "Error description";
        List<Placeholder> placeholders = Collections.singletonList(new Placeholder("placeholder", "value"));
        when(errorCodeUtil.getErrorDescription(errorCode, placeholders)).thenReturn(errorDescription);

        ValidationErrorDto errorDto = errorFactory.buildError(errorCode, placeholders);

        assertEquals(errorCode, errorDto.getErrorCode());
        assertEquals(errorDescription, errorDto.getDescription());
    }
}
package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ErrorCodeUtilTest {

    private final Properties properties;

    private final ErrorCodeUtil errorCodeUtil;

    ErrorCodeUtilTest() throws IOException {
        properties = Mockito.mock(Properties.class);
        errorCodeUtil = new ErrorCodeUtil(properties);
    }

    @Test
    public void shouldGetErrorDescription() {
        when(properties.getProperty("ERROR_CODE")).thenReturn("error description");
        assertEquals(errorCodeUtil.getErrorDescription("ERROR_CODE"), "error description");
    }

    @Test
    public void returnErrorDescriptionWithPlaceholder() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "VALUE");
        when(properties.getProperty("ERROR_CODE")).thenReturn("error {PLACEHOLDER} description");
        assertEquals(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder)),
                "error VALUE description");
    }
}

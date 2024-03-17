package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ErrorCodeUtilTest {
    @Mock
    private Properties properties;
    @InjectMocks
    private ErrorCodeUtil errorCodeUtil;

    @Test
    public void getErrorDescriptionCasualMethodTest() {
        when(properties.getProperty("ERROR_CODE")).thenReturn("error description");
        assertEquals(errorCodeUtil.getErrorDescription("ERROR_CODE"), "error description");
    }
    @Test
    public void getErrorDescriptionMethodWithPlaceholderTest() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER_1", "ERROR_1");
        when(properties.getProperty("ERROR_CODE")).thenReturn("error {PLACEHOLDER_1} description");
        assertEquals(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder)), "error ERROR_1 description");
    }
}

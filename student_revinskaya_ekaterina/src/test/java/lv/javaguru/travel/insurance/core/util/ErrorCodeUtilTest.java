package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ErrorCodeUtilTest {
    @InjectMocks
    private ErrorCodeUtil errorCodeUtil;
    @Mock
    private Environment environment;

    @Test
    public void descriptionByErrorCode() {
        when(environment.getProperty("ERROR_CODE")).thenReturn("description of code");
        assertEquals(errorCodeUtil.getErrorDescription("ERROR_CODE"), "description of code");
    }

    @Test
    public void descriptionByErrorCodeAndListOfPlaceholder() {
        when(environment.getProperty("ERROR_CODE")).thenReturn("{MISTAKE_RISK} is bad risk");
        assertEquals(errorCodeUtil.getErrorDescription("ERROR_CODE",
                        List.of(new Placeholder("MISTAKE_RISK", "SOME_RISK"))),
                "SOME_RISK is bad risk");
    }
}

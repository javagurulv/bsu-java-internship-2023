package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ErrorCodeUtilTest {
    @Mock
    Properties properties;
    @InjectMocks ErrorCodeUtil errorCodeUtil;


    @Test
    public void shouldReturnErrorDescription() {
        when(properties.getProperty("ERROR_CODE")).thenReturn("description");
        String description = errorCodeUtil.getErrorDescription("ERROR_CODE");
        assertThat(description).isEqualTo("description");
    }

    @Test
    public void shouldReturnErrorDescriptionWithPlaceholders() {
        when(properties.getProperty("ERROR_CODE")).thenReturn("smth {pl1} smth {pl2} smth");
        List<Placeholder> placeholders = List.of(new Placeholder("pl1", "value1"),
                new Placeholder("pl2", "value2"));
        String description = errorCodeUtil.getErrorDescription("ERROR_CODE", placeholders);
        assertThat(description).isEqualTo("smth value1 smth value2 smth");
    }

}

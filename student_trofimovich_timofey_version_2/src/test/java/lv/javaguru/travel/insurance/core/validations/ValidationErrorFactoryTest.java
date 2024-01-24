package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {
    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @InjectMocks
    private ValidationErrorFactory factory;

    @Test
    public void shouldBuildError() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE")).thenReturn("description");
        ValidationErrorDTO errorCode = factory.buildError("ERROR_CODE");
        assertThat(errorCode.getErrorCode()).isEqualTo("ERROR_CODE");
        assertThat(errorCode.getDescription()).isEqualTo("description");
    }

    @Test
    public void shouldBuildErrorWithPlaceholders() {
        List<Placeholder> placeholders = List.of(new Placeholder("name1", "value1"),
                new Placeholder("name2", "value2"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", placeholders)).thenReturn("description");
        ValidationErrorDTO errorCode = factory.buildError("ERROR_CODE", placeholders);
        assertThat(errorCode.getErrorCode()).isEqualTo("ERROR_CODE");
        assertThat(errorCode.getDescription()).isEqualTo("description");
    }
}

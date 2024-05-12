package lv.javaguru.travel.insurance.core.validations.get;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
public class CorrectUUIDValidationTest {
    @InjectMocks
    private CorrectUUIDValidation validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    private final String errorCode = "ERROR_CODE_18";
    private final String description = "Invalid agreement UUID!";

    private String uuid;

    @Test
    public void correctUUID() {
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        uuid = "12345678-1234-1234-1234-123456789101";

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isEmpty());
    }

    @Test
    public void UUIDIsNull() {
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isEmpty());
    }

    @Test
    public void veryShortUUID() {
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        uuid = "54";

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isPresent());
        assertEquals("", errorCode, optional.get().getErrorCode());
        assertEquals("", description, optional.get().getDescription());
    }

    @Test
    public void invalidUUID() {
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        uuid = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isPresent());
        assertEquals("", errorCode, optional.get().getErrorCode());
        assertEquals("", description, optional.get().getDescription());
    }

    @Test
    public void UUIDWithoutHyphens() {
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        uuid = "12345678123412341234123456789101";

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isPresent());
        assertEquals("", errorCode, optional.get().getErrorCode());
        assertEquals("", description, optional.get().getDescription());
    }

    @Test
    public void UUIDWithInvalidChar() {
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        uuid = "12345678-1234-123F-1234-123456789101";

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isPresent());
        assertEquals("", errorCode, optional.get().getErrorCode());
        assertEquals("", description, optional.get().getDescription());
    }
}

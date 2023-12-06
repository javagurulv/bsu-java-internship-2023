package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorManager;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {

    @Mock
    private ErrorManager errorManager;

    @InjectMocks
    private ValidationErrorFactory errorFactory;

    @Test
    public void errorCreation() {
        when(errorManager.getErrorDescription("ERROR_CODE")).thenReturn("description");
        ValidationError error = errorFactory.buildError("ERROR_CODE");

        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getDescription(), "description");
    }
}

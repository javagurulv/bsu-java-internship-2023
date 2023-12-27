package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryImplTest {
    @Mock
    ErrorCodeUtil propertyReader;
    @InjectMocks  ValidationErrorFactoryImpl validationErrorFactory;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(propertyReader);
    }
    @Test
    void createValidationError() {
        when(propertyReader.getProperty("ERROR_CODE")).thenReturn("Description");
        ValidationError actualValidationError = validationErrorFactory.createValidationError("ERROR_CODE");
        assertEquals("ERROR_CODE", actualValidationError.getErrorCode());
        assertEquals("Description", actualValidationError.getDescription());
    }
}
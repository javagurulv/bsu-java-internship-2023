package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UuidIsPresentValidationTest {
    @InjectMocks
    private UuidIsPresentValidation validation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Test
    public void uuidIsEmpty(){
        String uuid = "";
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_18")).thenReturn(error);
        assertTrue(validation.validate(uuid).isPresent());
        assertEquals(validation.validate(uuid).get(), error);
    }
    @Test
    public void uuidIsNull(){
        String uuid = null;
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_18")).thenReturn(error);
        assertTrue(validation.validate(uuid).isPresent());
        assertEquals(validation.validate(uuid).get(), error);
    }
    @Test
    public void uuidIsPresent(){
        String uuid = "465d35b7-57a7-1428-b1f2-6c23e5ed4462";
        assertTrue(validation.validate(uuid).isEmpty());
    }
}

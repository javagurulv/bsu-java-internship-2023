package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelUuidValidatorImplTest {
    @InjectMocks
    private TravelUuidValidatorImpl travelUuidValidator;
    @Mock
    private TravelUuidValidation uuidValidation1;
    @Mock
    private TravelUuidValidation uuidValidation2;
    @Test
    public void returnErrors(){
        String uuid = "UUID";
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(uuidValidation1.validate(uuid)).thenReturn(Optional.of(error));
        when(uuidValidation2.validate(uuid)).thenReturn(Optional.of(error));
        ReflectionTestUtils.setField(travelUuidValidator, "uuidValidations",
                List.of(uuidValidation1, uuidValidation2));
        assertEquals(travelUuidValidator.validate(uuid).size(), 2);
    }
    @Test
    public void returnNoErrors(){
        String uuid = "UUID";
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(uuidValidation1.validate(uuid)).thenReturn(Optional.empty());
        when(uuidValidation2.validate(uuid)).thenReturn(Optional.empty());
        ReflectionTestUtils.setField(travelUuidValidator, "uuidValidations",
                List.of(uuidValidation1, uuidValidation2));
        assertTrue(travelUuidValidator.validate(uuid).isEmpty());
    }

}

package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {
    @Mock private TravelRequestValidation validationObject;
    @Mock private  TravelCalculatePremiumRequest request;
    @InjectMocks private TravelCalculatePremiumRequestValidator validator;
    @Test
    void validateTestNotEmptyList() {
        when(validationObject.validate(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> expextedList = List.of(validationObject);
        ReflectionTestUtils.setField(validator, "validations", expextedList);
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    void validateTestEmptyList() {
        when(validationObject.validate(request)).thenReturn(Optional.of(new ValidationError("field", "message")));
        List<TravelRequestValidation> expextedList = List.of(validationObject);
        ReflectionTestUtils.setField(validator, "validations", expextedList);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals("field", errors.get(0).getField());
        assertEquals("message", errors.get(0).getMessage());
    }
}
package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PremiumRequestValidatorTest {
    @Mock private ValidationError expectedError;
    @Mock private TravelRequestValidation validationObject;
    @Mock private TravelCalculatePremiumRequestV1 request;
    @InjectMocks private PremiumRequestValidatorImp validator;
    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(validationObject);
        assertNotNull(request);
        assertNotNull(validator);
    }
    @Test
    void validateTestNotEmptyListTest() {
        when(validationObject.validate(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> expextedList = List.of(validationObject);
        ReflectionTestUtils.setField(validator, "validations", expextedList);
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    void validateTestEmptyListTest() {
        when(validationObject.validate(request)).thenReturn(Optional.of(expectedError));
        List<TravelRequestValidation> expextedList = List.of(validationObject);
        ReflectionTestUtils.setField(validator, "validations", expextedList);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(expectedError, errors.get(0));
    }
}
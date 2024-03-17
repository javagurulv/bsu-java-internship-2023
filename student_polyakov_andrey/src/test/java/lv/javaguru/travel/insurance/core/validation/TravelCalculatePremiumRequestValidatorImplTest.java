package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
public class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private TravelRequestValidation validation1;
    @Mock
    private TravelRequestValidation validation2;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void shouldNotReturnErrors() {
        when(validation1.validate(requestMock)).thenReturn(Optional.empty());
        when(validation2.validate(requestMock)).thenReturn(Optional.empty());
        when(validation1.validateList(requestMock)).thenReturn(List.of());
        when(validation2.validateList(requestMock)).thenReturn(List.of());
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void shouldReturnSingleErrors() {
        when(validation1.validate(requestMock)).thenReturn(Optional.of(new ValidationError()));
        when(validation2.validate(requestMock)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertEquals(errors.size(), 2);
    }
    @Test
    public void shouldReturnListOfErrors() {
        when(validation1.validateList(requestMock)).thenReturn(List.of(new ValidationError()));
        when(validation2.validateList(requestMock)).thenReturn(List.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertEquals(errors.size(), 2);
    }
}

package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl reqVal;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation1.validation(reqMock)).thenReturn(Optional.empty());
        when(validation2.validation(reqMock)).thenReturn(Optional.empty());
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(reqVal, "travelValidations", travelValidations);
        List<ValidationError> errors = reqVal.validate(reqMock);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation1.validation(reqMock)).thenReturn(Optional.of(new ValidationError()));
        when(validation2.validation(reqMock)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(reqVal, "travelValidations", travelValidations);
        List<ValidationError> errors = reqVal.validate(reqMock);
        assertEquals(errors.size(), 2);
    }
}

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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    TravelRequestValidation firstValidation;

    @Mock
    TravelRequestValidation secondValidation;

    @InjectMocks
    private final TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(firstValidation.execute(request)).thenReturn(Optional.of(new ValidationError()));
        when(secondValidation.execute(request)).thenReturn(Optional.of(new ValidationError()));
        ReflectionTestUtils.setField(requestValidator, "validations", List.of(firstValidation, secondValidation));

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(2, validationErrors.size());
    }

    @Test
    public void shouldReturnNoError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(firstValidation.execute(request)).thenReturn(Optional.empty());
        when(secondValidation.execute(request)).thenReturn(Optional.empty());
        ReflectionTestUtils.setField(requestValidator, "validations", List.of(firstValidation, secondValidation));

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertTrue(validationErrors.isEmpty());
    }
}

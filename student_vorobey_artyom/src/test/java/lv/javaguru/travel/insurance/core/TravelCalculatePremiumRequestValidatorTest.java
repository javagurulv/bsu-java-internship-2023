package lv.javaguru.travel.insurance.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    @Test
    public void personFirstNameShouldNotBeEmpty () {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void personFirstNameShouldNotBeNull () {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorIfPersonIsPresent () {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Vasya");
        List<ValidationError> error = validator.validate(request);
        Assertions.assertTrue(error.isEmpty());
    }
}

package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    @Test
    public void firstNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("");
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "personFirstName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void fistNameShouldNotBeNull() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "personFirstName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }
    @Test
    public void errorShouldNotBeCalledWhenFistNameIsPresent() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertTrue(errorsList.isEmpty());
    }
}

package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    void validateTestWithOneElementWithoutEmptyFields() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn("LastName");
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
        assertEquals(errors.size(), 0);
        assertEquals(request.getPersonFirstName(), "Mixail");
    }

    @Test
    void validateTestWithOneElementWithoutFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("LastName");

        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void validateTestWithoutTwoFirstName() { // why error?
        List<TravelCalculatePremiumRequest> mockList = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>(3);
        for (int i = 0; i < 2; i++) {
            TravelCalculatePremiumRequest elementMockEmpty = mock(TravelCalculatePremiumRequest.class);
            mockList.add(elementMockEmpty);
            when(mockList.get(i).getPersonFirstName()).thenReturn(null);
            errors.add(requestValidator.validate(mockList.get(i)).get(0));

        }
        TravelCalculatePremiumRequest elementMockNotEmpty = mock(TravelCalculatePremiumRequest.class);

        when(elementMockNotEmpty.getPersonFirstName()).thenReturn("Mixail");

        mockList.add(elementMockNotEmpty);
//        errors.add(requestValidator.validate(mockList.get(2)).get(0)); // why error?

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);

        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "personFirstName");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");

        assertEquals(elementMockNotEmpty.getPersonFirstName(), "Mixail");
    }

    @Test
    void validateTestWithoutTwoFirstName2() {
        List<TravelCalculatePremiumRequest> mockList = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>(3);
        for (int i = 0; i < 2; i++) {
            TravelCalculatePremiumRequest elementMockEmpty = mock(TravelCalculatePremiumRequest.class);
            mockList.add(elementMockEmpty);
            when(mockList.get(i).getPersonFirstName()).thenReturn(null);
            when(mockList.get(i).getPersonLastName()).thenReturn("LastName");
            List<ValidationError> resultErrors = requestValidator.validate(mockList.get(i));
            errors.addAll(resultErrors);
        }
        TravelCalculatePremiumRequest elementMockNotEmpty = mock(TravelCalculatePremiumRequest.class);
        mockList.add(elementMockNotEmpty);
        when(mockList.get(2).getPersonFirstName()).thenReturn("Mixail");
        when(mockList.get(2).getPersonLastName()).thenReturn("LastName");

        List<ValidationError> resultErrorsNotEmpty = requestValidator.validate(mockList.get(2));
        errors.addAll(resultErrorsNotEmpty);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);

        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "personFirstName");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");

        assertEquals(elementMockNotEmpty.getPersonFirstName(), "Mixail");
    }

    @Test
    void validateTestWithOneElementWithoutLastName()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void validateTestWithOneElementWithoutFirstNameAndLastName()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    void validateTestWithOneElementWithFirstNameAndLastName()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn("LastName");
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
        assertEquals(errors.size(), 0);
        assertEquals(request.getPersonFirstName(), "Mixail");
        assertEquals(request.getPersonLastName(), "LastName");
    }

    @Test
    void validateTestWithOneElementWithoutTwoLastName()
    {
        List<TravelCalculatePremiumRequest> mockList = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>(3);
        for (int i = 0; i < 2; i++) {
            TravelCalculatePremiumRequest elementMockEmpty = mock(TravelCalculatePremiumRequest.class);
            mockList.add(elementMockEmpty);
            when(mockList.get(i).getPersonFirstName()).thenReturn("Mixail");
            when(mockList.get(i).getPersonLastName()).thenReturn(null);
            List<ValidationError> resultErrors = requestValidator.validate(mockList.get(i));
            errors.addAll(resultErrors);
        }
        TravelCalculatePremiumRequest elementMockNotEmpty = mock(TravelCalculatePremiumRequest.class);
        mockList.add(elementMockNotEmpty);
        when(mockList.get(2).getPersonFirstName()).thenReturn("Mixail");
        when(mockList.get(2).getPersonLastName()).thenReturn("LastName");
        List<ValidationError> resultErrorsNotEmpty = requestValidator.validate(mockList.get(2));
        errors.addAll(resultErrorsNotEmpty);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);

        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");

        assertEquals(elementMockNotEmpty.getPersonFirstName(), "Mixail");
        assertEquals(elementMockNotEmpty.getPersonLastName(), "LastName");

    }
}
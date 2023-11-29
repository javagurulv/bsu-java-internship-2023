package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
    //@Mock
    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    void shouldReturnErrorAllRight() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01-01-2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10-01-2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
        assertEquals(errors.size(), 0);
        assertEquals(request.getPersonFirstName(), "Mixail");
        assertEquals(request.getPersonLastName(), "LastName");
        assertEquals(request.getAgreementDateFrom(), createDate("01-01-2023"));
        assertEquals(request.getAgreementDateTo(), createDate("10-01-2023"));
    }


    @Test
    void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01-01-2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10-01-2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01-01-2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10-01-2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }


    @Test
    void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Maxail");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01-01-2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10-01-2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01-01-2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10-01-2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(createDate("10-01-2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenAgreementDateToIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Mixail");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("10-01-2023"));
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


//    @Test
//    void validateTestWithoutTwoFirstName() { // why error?
//        List<TravelCalculatePremiumRequest> mockList = new ArrayList<>();
//        List<ValidationError> errors = new ArrayList<>(3);
//        for (int i = 0; i < 2; i++) {
//            TravelCalculatePremiumRequest elementMockEmpty = mock(TravelCalculatePremiumRequest.class);
//            mockList.add(elementMockEmpty);
//            when(mockList.get(i).getPersonFirstName()).thenReturn(null);
//            errors.add(requestValidator.validate(mockList.get(i)).get(0));
//
//        }
//        TravelCalculatePremiumRequest elementMockNotEmpty = mock(TravelCalculatePremiumRequest.class);
//
//        when(elementMockNotEmpty.getPersonFirstName()).thenReturn("Mixail");
//
//        mockList.add(elementMockNotEmpty);
////        errors.add(requestValidator.validate(mockList.get(2)).get(0)); // why error?
//
//        assertFalse(errors.isEmpty());
//        assertEquals(errors.size(), 2);
//
//        assertEquals(errors.get(0).getField(), "personFirstName");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//        assertEquals(errors.get(1).getField(), "personFirstName");
//        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
//
//        assertEquals(elementMockNotEmpty.getPersonFirstName(), "Mixail");
//    }
//
//    @Test
//    void validateTestWithoutTwoFirstName2() {
//        List<TravelCalculatePremiumRequest> mockList = new ArrayList<>();
//        List<ValidationError> errors = new ArrayList<>(3);
//        for (int i = 0; i < 2; i++) {
//            TravelCalculatePremiumRequest elementMockEmpty = mock(TravelCalculatePremiumRequest.class);
//            mockList.add(elementMockEmpty);
//            when(mockList.get(i).getPersonFirstName()).thenReturn(null);
//            when(mockList.get(i).getPersonLastName()).thenReturn("LastName");
//            List<ValidationError> resultErrors = requestValidator.validate(mockList.get(i));
//            errors.addAll(resultErrors);
//        }
//        TravelCalculatePremiumRequest elementMockNotEmpty = mock(TravelCalculatePremiumRequest.class);
//        mockList.add(elementMockNotEmpty);
//        when(mockList.get(2).getPersonFirstName()).thenReturn("Mixail");
//        when(mockList.get(2).getPersonLastName()).thenReturn("LastName");
//
//        List<ValidationError> resultErrorsNotEmpty = requestValidator.validate(mockList.get(2));
//        errors.addAll(resultErrorsNotEmpty);
//
//        assertFalse(errors.isEmpty());
//        assertEquals(errors.size(), 2);
//
//        assertEquals(errors.get(0).getField(), "personFirstName");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//        assertEquals(errors.get(1).getField(), "personFirstName");
//        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
//
//        assertEquals(elementMockNotEmpty.getPersonFirstName(), "Mixail");
//    }
//
//    @Test
//    void validateTestWithOneElementWithoutLastName()
//    {
//        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        when(request.getPersonFirstName()).thenReturn("Mixail");
//        when(request.getPersonLastName()).thenReturn(null);
//        List<ValidationError> errors = requestValidator.validate(request);
//        assertFalse(errors.isEmpty());
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "personLastName");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    void validateTestWithOneElementWithoutFirstNameAndLastName()
//    {
//        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        when(request.getPersonFirstName()).thenReturn(null);
//        when(request.getPersonLastName()).thenReturn(null);
//        List<ValidationError> errors = requestValidator.validate(request);
//        assertFalse(errors.isEmpty());
//        assertEquals(errors.size(), 2);
//        assertEquals(errors.get(0).getField(), "personFirstName");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//        assertEquals(errors.get(1).getField(), "personLastName");
//        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    void validateTestWithOneElementWithFirstNameAndLastName()
//    {
//        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        when(request.getPersonFirstName()).thenReturn("Mixail");
//        when(request.getPersonLastName()).thenReturn("LastName");
//        List<ValidationError> errors = requestValidator.validate(request);
//        assertTrue(errors.isEmpty());
//        assertEquals(errors.size(), 0);
//        assertEquals(request.getPersonFirstName(), "Mixail");
//        assertEquals(request.getPersonLastName(), "LastName");
//    }
//
//    @Test
//    void validateTestWithOneElementWithoutTwoLastName()
//    {
//        List<TravelCalculatePremiumRequest> mockList = new ArrayList<>();
//        List<ValidationError> errors = new ArrayList<>(3);
//        for (int i = 0; i < 2; i++) {
//            TravelCalculatePremiumRequest elementMockEmpty = mock(TravelCalculatePremiumRequest.class);
//            mockList.add(elementMockEmpty);
//            when(mockList.get(i).getPersonFirstName()).thenReturn("Mixail");
//            when(mockList.get(i).getPersonLastName()).thenReturn(null);
//            List<ValidationError> resultErrors = requestValidator.validate(mockList.get(i));
//            errors.addAll(resultErrors);
//        }
//        TravelCalculatePremiumRequest elementMockNotEmpty = mock(TravelCalculatePremiumRequest.class);
//        mockList.add(elementMockNotEmpty);
//        when(mockList.get(2).getPersonFirstName()).thenReturn("Mixail");
//        when(mockList.get(2).getPersonLastName()).thenReturn("LastName");
//        List<ValidationError> resultErrorsNotEmpty = requestValidator.validate(mockList.get(2));
//        errors.addAll(resultErrorsNotEmpty);
//
//        assertFalse(errors.isEmpty());
//        assertEquals(errors.size(), 2);
//
//        assertEquals(errors.get(0).getField(), "personLastName");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//        assertEquals(errors.get(1).getField(), "personLastName");
//        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
//
//        assertEquals(elementMockNotEmpty.getPersonFirstName(), "Mixail");
//        assertEquals(elementMockNotEmpty.getPersonLastName(), "LastName");
//
//    }
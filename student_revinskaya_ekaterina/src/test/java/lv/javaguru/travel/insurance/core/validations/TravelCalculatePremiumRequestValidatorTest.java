package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
@InjectMocks
private TravelCalculatePremiumRequestValidatorImpl requestValidator;
    @Mock
    private TravelCalculatePremiumRequestV1 request;
    @Test
    public void responseShouldContainSingleErrorsTest() {
        TravelRequestValidation requestSingleValidation = mock(TravelRequestValidation.class);
        TravelRequestValidation requestListValidation = mock(TravelRequestValidation.class);
        when(requestSingleValidation.validate(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestListValidation.validateList(request)).thenReturn(List.of());
        List<TravelRequestValidation> travelValidations = List.of(
                requestSingleValidation, requestListValidation
        );
        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
    }
    @Test
    public void responseShouldContainListErrorsTest() {
        TravelRequestValidation requestSingleValidation = mock(TravelRequestValidation.class);
        TravelRequestValidation requestListValidation = mock(TravelRequestValidation.class);
        when(requestSingleValidation.validate(request)).thenReturn(Optional.empty());
        when(requestListValidation.validateList(request)).thenReturn(List.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                requestSingleValidation, requestListValidation
        );
        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
    }
    @Test
    public void responseShouldContainSingleAndListErrorsTest() {
        TravelRequestValidation requestSingleValidation = mock(TravelRequestValidation.class);
        TravelRequestValidation requestListValidation = mock(TravelRequestValidation.class);
        when(requestSingleValidation.validate(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestListValidation.validateList(request)).thenReturn(List.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                requestSingleValidation, requestListValidation
        );
        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }


    @Test
    public void responseNotContainErrorTest() {
        TravelRequestValidation requestValidation = mock(TravelRequestValidation.class);
        when(requestValidation.validate(request)).thenReturn(Optional.empty());
        when(requestValidation.validateList(request)).thenReturn(List.of());
        List<TravelRequestValidation> travelValidations = List.of(
                requestValidation
        );
        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }

}
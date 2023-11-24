package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of());
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of());

        TravelPersonFieldValidation validation3 = mock(TravelPersonFieldValidation.class);
        when(validation3.validate(request)).thenReturn(Optional.empty());
        when(validation3.validateList(request)).thenReturn(List.of());
        TravelPersonFieldValidation validation4 = mock(TravelPersonFieldValidation.class);
        when(validation4.validate(request)).thenReturn(Optional.empty());
        when(validation4.validateList(request)).thenReturn(List.of());

        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personValidations = List.of(validation3, validation4);
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSingleAgreementErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personValidations = List.of();
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        List<TravelAgreementFieldValidation> agreementValidations = List.of();
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnListAgreementErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of(new ValidationError()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of(new ValidationError()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personValidations = List.of();
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnListPersonErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of(new ValidationError()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of(new ValidationError()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of();
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

}
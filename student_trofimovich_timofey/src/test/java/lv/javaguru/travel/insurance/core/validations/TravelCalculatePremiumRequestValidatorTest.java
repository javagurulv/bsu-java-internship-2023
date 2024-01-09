package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


public class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImplementation requestValidator;


    @Test
    void shouldNotReturnErrors() {

        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation1.validate(request)).thenReturn(Optional.empty());
        when(agreementValidation1.validateList(request)).thenReturn(List.of());

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation2.validate(request)).thenReturn(Optional.empty());
        when(agreementValidation2.validateList(request)).thenReturn(List.of());

        TravelPersonFieldValidation personVal1 = mock(TravelPersonFieldValidation.class);
        when(personVal1.validate(request)).thenReturn(Optional.empty());
        when(personVal1.validateList(request)).thenReturn(List.of());

        TravelPersonFieldValidation personVal2 = mock(TravelPersonFieldValidation.class);
        when(personVal2.validate(request)).thenReturn(Optional.empty());
        when(personVal2.validateList(request)).thenReturn(List.of());

        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementValidation1, agreementValidation2);
        List<TravelPersonFieldValidation> personValidationsList = List.of(personVal1, personVal2);
        ReflectionTestUtils.setField(requestValidator, "agreementValidations", agreementValidationsList);
        ReflectionTestUtils.setField(requestValidator, "personValidations", personValidationsList);

        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnAgreementListErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation1.validate(request)).thenReturn(Optional.empty());
        when(agreementValidation1.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation2.validate(request)).thenReturn(Optional.empty());
        when(agreementValidation2.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));

        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementValidation1, agreementValidation2);
        ReflectionTestUtils.setField(requestValidator, "agreementValidations", agreementValidationsList);
        ReflectionTestUtils.setField(requestValidator, "personValidations", List.of());

        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnListPersonErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        TravelPersonFieldValidation personVal1 = mock(TravelPersonFieldValidation.class);
        when(personVal1.validate(request)).thenReturn(Optional.empty());
        when(personVal1.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));

        TravelPersonFieldValidation personVal2 = mock(TravelPersonFieldValidation.class);
        when(personVal2.validate(request)).thenReturn(Optional.empty());
        when(personVal2.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));

        List<TravelPersonFieldValidation> personValidationsList = List.of(personVal1, personVal2);
        ReflectionTestUtils.setField(requestValidator, "agreementValidations", List.of());
        ReflectionTestUtils.setField(requestValidator, "personValidations", personValidationsList);

        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnSingleAgreementErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation1.validate(request)).thenReturn(Optional.of(mock(ValidationError.class)));
        //when(agreementValidation1.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation2.validate(request)).thenReturn(Optional.of(mock(ValidationError.class)));
        //when(agreementValidation2.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));


        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementValidation1, agreementValidation2);
        ReflectionTestUtils.setField(requestValidator, "agreementValidations", agreementValidationsList);
        ReflectionTestUtils.setField(requestValidator, "personValidations", List.of());

        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnSinglePersonErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        TravelPersonFieldValidation personVal1 = mock(TravelPersonFieldValidation.class);
        when(personVal1.validate(request)).thenReturn(Optional.of(mock(ValidationError.class)));
        /*when(personVal1.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));*/

        TravelPersonFieldValidation personVal2 = mock(TravelPersonFieldValidation.class);
        when(personVal2.validate(request)).thenReturn(Optional.of(mock(ValidationError.class)));
        /*when(personVal2.validateList(request)).thenReturn(List.of(mock(ValidationError.class)));*/

        List<TravelPersonFieldValidation> personValidationsList = List.of(personVal1, personVal2);
        ReflectionTestUtils.setField(requestValidator, "agreementValidations", List.of());
        ReflectionTestUtils.setField(requestValidator, "personValidations", personValidationsList);

        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

}

package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.agreement.TravelAgreementFieldValidationImpl;
import lv.javaguru.travel.insurance.core.validations.person.TravelPersonFieldValidationImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TravelAgreementValidatorImplTest {
    @InjectMocks
    private TravelAgreementValidatorImpl validator = new TravelAgreementValidatorImpl();


    private AgreementDTO agreement = mock(AgreementDTO.class);

    private PersonDTO person = mock(PersonDTO.class);
    private void init(Optional<ValidationErrorDTO> v1,
                      Optional<ValidationErrorDTO> v2,
                      Optional<ValidationErrorDTO> v3,
                      Optional<ValidationErrorDTO> v4) {
        when(agreement.getPersons()).thenReturn(List.of(person));

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidationImpl.class);
        when(agreementValidation1.validate(agreement)).thenReturn(v1);
        when(agreementValidation1.validateList(agreement)).thenReturn(List.of());

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidationImpl.class);
        when(agreementValidation2.validate(agreement)).thenReturn(v2);
        when(agreementValidation2.validateList(agreement)).thenReturn(List.of());

        TravelPersonFieldValidation personValidation1 = mock(TravelPersonFieldValidationImpl.class);
        when(personValidation1.validate(person)).thenReturn(v3);
        when(personValidation1.validateList(person)).thenReturn(List.of());

        TravelPersonFieldValidation personValidation2 = mock(TravelPersonFieldValidationImpl.class);
        when(personValidation2.validate(person)).thenReturn(v4);
        when(personValidation2.validateList(person)).thenReturn(List.of());

        ReflectionTestUtils.setField(validator, "agreementValidations", List.of(agreementValidation1, agreementValidation2));
        ReflectionTestUtils.setField(validator, "personValidations", List.of(personValidation1, personValidation2));
    }
    @Test
    public void shouldNotReturnErrors() {
        init(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        assertTrue("", validator.validate(agreement).isEmpty());
    }

    @Test
    public void shouldReturnSingleAgreementErrors() {
        init(Optional.of(new ValidationErrorDTO()),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        assertEquals("", 1, validator.validate(agreement).size());
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        init(Optional.empty(),
                Optional.empty(),
                Optional.of(new ValidationErrorDTO()),
                Optional.empty());
        assertEquals("", 1, validator.validate(agreement).size());
    }

    @Test
    public void shouldReturnListAgreementErrors() {
        init(Optional.of(new ValidationErrorDTO()),
                Optional.of(new ValidationErrorDTO()),
                Optional.empty(),
                Optional.empty());
        assertEquals("", 2, validator.validate(agreement).size());
    }

    @Test
    public void shouldReturnListPersonErrors() {
        init(Optional.empty(),
                Optional.empty(),
                Optional.of(new ValidationErrorDTO()),
                Optional.of(new ValidationErrorDTO()));
        assertEquals("", 2, validator.validate(agreement).size());
    }


}

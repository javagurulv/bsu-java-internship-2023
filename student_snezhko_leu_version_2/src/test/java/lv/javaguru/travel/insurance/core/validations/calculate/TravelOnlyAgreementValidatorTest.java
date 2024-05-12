package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelAgreementFieldValidation;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelOnlyAgreementValidatorImpl;
import lv.javaguru.travel.insurance.core.validations.calculate.agreement.TravelAgreementFieldValidationImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
public class TravelOnlyAgreementValidatorTest {
    @InjectMocks
    private TravelOnlyAgreementValidatorImpl validator;

    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;
    private void init(Optional<ValidationErrorDTO> v1,
                      Optional<ValidationErrorDTO> v2) {
        when(agreement.getPersons()).thenReturn(List.of(person));

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidationImpl.class);
        when(agreementValidation1.validate(agreement)).thenReturn(v1);
        when(agreementValidation1.validateList(agreement)).thenReturn(List.of());

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidationImpl.class);
        when(agreementValidation2.validate(agreement)).thenReturn(v2);
        when(agreementValidation2.validateList(agreement)).thenReturn(List.of());


        ReflectionTestUtils.setField(validator, "agreementValidations", List.of(agreementValidation1, agreementValidation2));
        //ReflectionTestUtils.setField(validator, "personValidations", List.of(personValidation1, personValidation2));


    }
    @Test
    public void shouldNotReturnErrors() {
        init(Optional.empty(), Optional.empty());
        assertTrue("", validator.validate(agreement).isEmpty());
    }

    @Test
    public void shouldReturnSingleAgreementErrors() {
        init(Optional.of(new ValidationErrorDTO()),
                Optional.empty());
        assertEquals("", 1, validator.validate(agreement).size());
    }

    @Test
    public void shouldReturnListAgreementErrors() {
        init(Optional.of(new ValidationErrorDTO()),
                Optional.of(new ValidationErrorDTO()));
        assertEquals("", 2, validator.validate(agreement).size());
    }
}

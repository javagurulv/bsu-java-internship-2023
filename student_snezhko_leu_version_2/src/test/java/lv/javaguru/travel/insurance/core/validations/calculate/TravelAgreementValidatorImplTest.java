package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelAgreementValidatorImpl;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelOnlyAgreementValidatorImpl;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelOnlyPersonValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
public class TravelAgreementValidatorImplTest {

    @Mock
    private TravelOnlyPersonValidatorImpl personValidator;


    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;
    @InjectMocks
    private TravelAgreementValidatorImpl validator = new TravelAgreementValidatorImpl();

    @Mock
    private TravelOnlyAgreementValidatorImpl agreementValidator;
    private void init(Optional<ValidationErrorDTO> v1,
                      Optional<ValidationErrorDTO> v2,
                      Optional<ValidationErrorDTO> v3,
                      Optional<ValidationErrorDTO> v4) {
        when(agreement.getPersons()).thenReturn(List.of(person));

//        ReflectionTestUtils.setField(validator, "agreementValidations", List.of(agreementValidation1, agreementValidation2));
  //      ReflectionTestUtils.setField(validator, "personValidations", List.of(personValidation1, personValidation2));

        List<ValidationErrorDTO> agreementErrors = new ArrayList<>();
        v1.ifPresent(agreementErrors::add);
        v2.ifPresent(agreementErrors::add);

        List<ValidationErrorDTO> personErrors = new ArrayList<>();
        v3.ifPresent(personErrors::add);
        v4.ifPresent(personErrors::add);

        when(agreementValidator.validate(agreement)).thenReturn(agreementErrors);
        when(personValidator.validate(agreement)).thenReturn(personErrors);

        //ReflectionTestUtils.setField(validator, "agreementValidator", agreementValidator);
        //ReflectionTestUtils.setField(validator, "personValidator", personValidator);
        //ReflectionTestUtils.setField(validator, "personValidator", personValidator);
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

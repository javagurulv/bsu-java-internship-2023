package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.validations.calculate.person.TravelPersonFieldValidationImpl;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractPersonFieldValidationTest {
    protected TravelPersonFieldValidationImpl validation;

    protected ValidationErrorFactory errorFactory;

    protected PersonDTO person;

    protected String errorCode;
    protected String description;

    void init(String eCode, String descr, TravelPersonFieldValidationImpl valid) {
        validation = valid;
        errorCode = eCode;
        description = descr;

        errorFactory = mock(ValidationErrorFactory.class);
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        ReflectionTestUtils.setField(validation, "errorFactory", errorFactory);


        person = mock(PersonDTO.class);
    }

}

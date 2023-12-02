package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementFieldValidatorImplTest {
    @InjectMocks
    private AgreementFieldValidation agreementFieldValidation;
    @Mock
    private AgreementDTO agreementDTO;
    @Mock
    private ValidationErrorDTO validationError;
    @Test
    public void validateWithListOfAgreementSingleValidationsTest(){
        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        ReflectionTestUtils.setField(agreementFieldValidation, "agreementFieldValidations",
                List.of(agreementValidation1, agreementValidation2));
        when(agreementValidation1.validate(agreementDTO)).thenReturn(Optional.of(validationError));
        when(agreementValidation2.validate(agreementDTO)).thenReturn(Optional.of(validationError));

        assertEquals(agreementFieldValidation.validateErrors(agreementDTO).size(), 2);
    }
    @Test
    public void validateWithListOfAgreementListValidationsTest(){
        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        ReflectionTestUtils.setField(agreementFieldValidation, "agreementFieldValidations",
                List.of(agreementValidation1, agreementValidation2));
        when(agreementValidation1.validateList(agreementDTO)).thenReturn(List.of(validationError));
        when(agreementValidation2.validateList(agreementDTO)).thenReturn(List.of(validationError));

        assertEquals(agreementFieldValidation.validateErrors(agreementDTO).size(), 2);
    }
}

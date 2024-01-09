package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelAgreementValidatorImplTest {
    @InjectMocks
    private TravelAgreementValidatorImpl travelAgreementValidatorImpl;
    @Mock
    private AgreementDTO agreementDTO;
    @Mock
    private ListPersonFieldValidations listPersonFieldValidations;
    @Mock
    private AgreementFieldValidation agreementFieldValidation;
    @Mock
    private ValidationErrorDTO validationError;
    @Test
    public void notReturnErrors(){
        when(agreementFieldValidation.validateErrors(agreementDTO)).thenReturn(List.of());
        when(listPersonFieldValidations.validateErrors(agreementDTO)).thenReturn(List.of());
        assertTrue(travelAgreementValidatorImpl.validate(agreementDTO).isEmpty());
    }
    @Test
    public void returnAgreementErrors(){
        when(agreementFieldValidation.validateErrors(agreementDTO)).thenReturn(List.of(validationError));
        when(listPersonFieldValidations.validateErrors(agreementDTO)).thenReturn(List.of());
        assertEquals(travelAgreementValidatorImpl.validate(agreementDTO).size(), 1);
    }
    @Test
    public void returnPersonErrors(){
        when(agreementFieldValidation.validateErrors(agreementDTO)).thenReturn(List.of());
        when(listPersonFieldValidations.validateErrors(agreementDTO)).thenReturn(List.of(validationError));
        assertEquals(travelAgreementValidatorImpl.validate(agreementDTO).size(), 1);
    }
    @Test
    public void returnBothErrors(){
        when(agreementFieldValidation.validateErrors(agreementDTO)).thenReturn(List.of(validationError));
        when(listPersonFieldValidations.validateErrors(agreementDTO)).thenReturn(List.of(validationError));
        assertEquals(travelAgreementValidatorImpl.validate(agreementDTO).size(), 2);
    }




}

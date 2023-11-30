package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ExistClassifierValueForCountryValidationTest {
    @InjectMocks
    private ExistClassifierValueForCountryValidation existClassifierValueForCountryValidation;
@Mock private ClassifierValueRepository classifierValueRepository;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainErrorNotExistClassifierValueTest() {
        AgreementDTO request = mock(AgreementDTO.class);
        when(request.getCountry()).thenReturn("ENGLAND");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "ENGLAND"))
                .thenReturn(Optional.empty());
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_11"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= existClassifierValueForCountryValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldNotContainErrorNotExistClassifierValueTest() {
        AgreementDTO request = mock(AgreementDTO.class);
        when(request.getCountry()).thenReturn(null);
        Optional<ValidationErrorDTO> error= existClassifierValueForCountryValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}

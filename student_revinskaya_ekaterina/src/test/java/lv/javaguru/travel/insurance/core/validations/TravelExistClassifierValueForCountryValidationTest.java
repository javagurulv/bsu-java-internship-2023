package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
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
public class TravelExistClassifierValueForCountryValidationTest {
    @InjectMocks
    private TravelRequestExistClassifierValueForCountryValidation existClassifierValueForCountryValidation;
@Mock private ClassifierValueRepository classifierValueRepository;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainErrorNotExistClassifierValueTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("ENGLAND");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "ENGLAND"))
                .thenReturn(Optional.empty());
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_11"), anyList())).thenReturn(validationError);
        Optional<ValidationError> error= existClassifierValueForCountryValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldNotContainErrorNotExistClassifierValueTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn(null);
        Optional<ValidationError> error= existClassifierValueForCountryValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}

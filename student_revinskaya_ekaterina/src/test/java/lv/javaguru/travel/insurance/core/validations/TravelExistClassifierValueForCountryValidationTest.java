package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
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
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn(null);
        Optional<ValidationError> error= existClassifierValueForCountryValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}

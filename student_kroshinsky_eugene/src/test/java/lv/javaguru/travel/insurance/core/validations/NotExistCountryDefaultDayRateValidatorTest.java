package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotExistCountryDefaultDayRateValidatorTest {
    @Mock private ClassifierValue classifierValue;
    @Mock private ValidationErrorFactory validationErrorFactory;
    @Mock private ClassifierValueRepository valueRepository;
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private ValidationError validationError;
    @InjectMocks private NotExistCountryDefaultDayRateValidator validator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
        assertNotNull(valueRepository);
        assertNotNull(validationError);
    }
    @Test
    void validateNoErrorsTest() {
        when(request.getCountry()).thenReturn("SPAIN");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(valueRepository.findByClassifierTitleAndIc("COUNTRY","SPAIN"))
                .thenReturn(Optional.of(classifierValue));
        Optional<ValidationError> actualList = validator.validate(request);
        assertTrue(actualList.isEmpty());
    }
    @Test
    void validateListWithErrorsTest() {
        when(request.getCountry()).thenReturn("ERROR");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(valueRepository.findByClassifierTitleAndIc("COUNTRY","ERROR"))
                .thenReturn(Optional.empty());
        when(validationErrorFactory.buildError(anyString(), anyList()))
                .thenReturn(validationError);
        Optional<ValidationError> actualError = validator.validate(request);
        assertTrue(actualError.isPresent());
        assertEquals(validationError, actualError.get());
    }
}
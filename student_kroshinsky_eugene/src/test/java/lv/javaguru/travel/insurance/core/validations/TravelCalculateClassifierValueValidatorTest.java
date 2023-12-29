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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculateClassifierValueValidatorTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private ClassifierValueRepository valueRepository;
    @Mock
    private TravelCalculatePremiumRequest request;
    @Mock
    private ValidationError validationError;
    @InjectMocks
    TravelCalculateClassifierValueValidator validator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
        assertNotNull(valueRepository);
    }
    @Test
    void validateListNoErrorsTest() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_CANCELLATION", "TRAVEL_EVACUATION"));
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE","TRAVEL_CANCELLATION"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE","TRAVEL_EVACUATION"))
                .thenReturn(Optional.of(new ClassifierValue()));
        List<ValidationError> actualList = validator.validateList(request);
        assertTrue(actualList.isEmpty());
    }
    @Test
    void validateListWithErrorsTest() {
        when(request.getSelectedRisks()).thenReturn(List.of("ERROR", "TRAVEL_EVACUATION"));
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE","ERROR"))
                .thenReturn(Optional.empty());
        when(valueRepository.findByClassifierTitleAndIc("RISK_TYPE","TRAVEL_EVACUATION"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(validationErrorFactory.createValidationError("NOT_EXISTING_RISK"))
                .thenReturn(validationError);
        List<ValidationError> actualList = validator.validateList(request);
        assertTrue(!actualList.isEmpty());
        assertEquals(1, actualList.size());
        assertEquals(validationError, actualList.get(0));
    }
}
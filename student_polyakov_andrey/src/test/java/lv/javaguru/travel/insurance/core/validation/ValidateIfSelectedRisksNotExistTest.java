package lv.javaguru.travel.insurance.core.validation;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateIfSelectedRisksNotExistTest {
    @Mock
    private ValidationErrorFactory factoryMock;
    @Mock
    private ClassifierValueRepository classifierValueRepositoryMock;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @InjectMocks
    private ValidateIfSelectedRisksNotExist validator = new ValidateIfSelectedRisksNotExist();

    @Test
    public void NotValidateWhenSelectedRisksIsNull() {
        when(requestMock.getSelected_risks()).thenReturn(null);
        assertTrue(validator.validateList(requestMock).isEmpty());
        verifyNoInteractions(classifierValueRepositoryMock, factoryMock);
    }
    @Test
    public  void ValidationWithoutErrors() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("ERROR_1", "ERROR_2"));
        when(classifierValueRepositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "ERROR_1")).thenReturn(Optional.of(mock(ClassifierValue.class)));
        when(classifierValueRepositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "ERROR_2")).thenReturn(Optional.of(mock(ClassifierValue.class)));
        assertTrue(validator.validateList(requestMock).isEmpty());
    }
    @Test
    public  void ValidationWithErrors() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("ERROR_1", "ERROR_2"));
        when(classifierValueRepositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "ERROR_1")).thenReturn(Optional.empty());
        when(classifierValueRepositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "ERROR_2")).thenReturn(Optional.empty());
        when(factoryMock.createError(eq("ERROR_CODE_9"), anyList())).thenReturn(mock(ValidationError.class));
        assertEquals(validator.validateList(requestMock).size(), 2);
    }
}

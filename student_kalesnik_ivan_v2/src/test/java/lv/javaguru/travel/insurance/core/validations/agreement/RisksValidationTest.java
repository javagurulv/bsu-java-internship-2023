package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RisksValidationTest {

    @InjectMocks
    private RisksValidation validation;

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmptyListWhenSelectedRisksIsNull() {
        AgreementDto request = new AgreementDto();
        request.setSelectedRisks(null);

        List<ValidationErrorDto> errorList = validation.validateList(request);

        assertTrue(errorList.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenSelectedRisksIsEmpty() {
        AgreementDto request = new AgreementDto();
        request.setSelectedRisks(Collections.emptyList());

        List<ValidationErrorDto> errorList = validation.validateList(request);

        assertTrue(errorList.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenSelectedRisksContainExistingRiskTypes() {
        String riskIc1 = "RISK_TYPE_1";
        String riskIc2 = "RISK_TYPE_2";
        List<String> selectedRisks = List.of(riskIc1, riskIc2);
        AgreementDto request = new AgreementDto();
        request.setSelectedRisks(selectedRisks);

        when(classifierValueRepository.findByClassifierTitleAndIc(eq("RISK_TYPE"), anyString())).thenReturn(Optional.ofNullable(new ClassifierValue()));
        when(errorFactory.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(new ValidationErrorDto());

        List<ValidationErrorDto> errorList = validation.validateList(request);

        assertTrue(errorList.isEmpty());
    }
}
package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmptyMedicalRiskLimitLevelValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyMedicalRiskLimitLevelValidation validation;


    private AgreementDto agreement;
    private PersonDto person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        agreement = new AgreementDto();
        person = new PersonDto();
    }

    @Test
    void shouldReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndNullOrBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person.setMedicalRiskLimitLevel(null);
        ValidationErrorDto expectedError = mock(ValidationErrorDto.class);
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(expectedError);

        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);

        Optional<ValidationErrorDto> result = validation.validate(person,agreement);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndIsNotBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person.setMedicalRiskLimitLevel("LEVEL_10000");
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
        Optional<ValidationErrorDto> result = validation.validate(person,agreement);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelNotEnabledAndIsBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person.setMedicalRiskLimitLevel("");
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationErrorDto> result = validation.validate(person,agreement);
        assertTrue(result.isEmpty());
    }
}
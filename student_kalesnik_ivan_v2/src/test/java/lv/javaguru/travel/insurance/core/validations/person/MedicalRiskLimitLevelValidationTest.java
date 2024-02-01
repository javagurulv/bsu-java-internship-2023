package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalRiskLimitLevelValidationTest {

    @InjectMocks
    private MedicalRiskLimitLevelValidation validation;

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void shouldReturnEmptyOptionalWhenMedicalRiskLimitLevelIsNull() {
        PersonDto request = new PersonDto();
        request.setMedicalRiskLimitLevel(null);

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenMedicalRiskLimitLevelIsBlank() {
        PersonDto request = new PersonDto();
        request.setMedicalRiskLimitLevel(" ");

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }
}
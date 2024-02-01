package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SelectedRisksValidationTest {

    @InjectMocks
    private SelectedRisksValidation validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnErrorWhenSelectedRisksIsNull() {
        AgreementDto request = new AgreementDto();
        request.setSelectedRisks(null);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenSelectedRisksIsEmpty() {
        AgreementDto request = new AgreementDto();
        request.setSelectedRisks(List.of());
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldNotReturnErrorWhenSelectedRisksIsNotNullOrEmpty() {
        AgreementDto request = new AgreementDto();
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertFalse(errorOpt.isPresent());
    }
}
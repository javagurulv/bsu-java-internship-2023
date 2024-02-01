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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FirstNameValidationRequestTest {

    @InjectMocks
    private FirstNameValidationRequest validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmptyOptionalWhenPersonFirstNameIsNotNullOrEmpty() {
        PersonDto request = new PersonDto();
        request.setPersonFirstName("John");

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenPersonFirstNameIsNull() {
        PersonDto request = new PersonDto();
        request.setPersonFirstName(null);

        when(errorFactory.buildError(eq("ERROR_CODE_7"))).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        PersonDto request = new PersonDto();
        request.setPersonFirstName("");

        when(errorFactory.buildError(eq("ERROR_CODE_7"))).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertTrue(errorOpt.isPresent());
    }
}
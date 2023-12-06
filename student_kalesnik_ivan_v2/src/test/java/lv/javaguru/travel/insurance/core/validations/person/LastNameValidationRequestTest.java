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

class LastNameValidationRequestTest {

    @InjectMocks
    private LastNameValidationRequest validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmptyOptionalWhenPersonLastNameIsNotNullOrEmpty() {
        PersonDto request = new PersonDto();
        request.setPersonLastName("Doe");

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenPersonLastNameIsNull() {
        PersonDto request = new PersonDto();
        request.setPersonLastName(null);

        when(errorFactory.buildError(eq("ERROR_CODE_8"))).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        PersonDto request = new PersonDto();
        request.setPersonLastName("");

        when(errorFactory.buildError(eq("ERROR_CODE_8"))).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertTrue(errorOpt.isPresent());
    }
}
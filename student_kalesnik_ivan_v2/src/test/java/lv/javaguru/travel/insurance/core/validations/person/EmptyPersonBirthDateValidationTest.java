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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmptyPersonBirthDateValidationTest {

    @InjectMocks
    private EmptyPersonBirthDateValidation validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmptyOptionalWhenPersonBirthDateIsNotNull() {
        PersonDto request = new PersonDto();
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            request.setPersonBirthDate(simpleDateFormat.parse("01.01.1990"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenPersonBirthDateIsNull() {
        PersonDto request = new PersonDto();
        request.setPersonBirthDate(null);

        when(errorFactory.buildError(eq("ERROR_CODE_11"))).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertTrue(errorOpt.isPresent());
    }
}
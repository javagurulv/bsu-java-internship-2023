package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonBirthDateInThePastValidationTest {

    @InjectMocks
    private PersonBirthDateInThePastValidation validation;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldReturnErrorWhenPersonBirthDateIsBeforeCurrentDateTime() {
        PersonDto request = new PersonDto();
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date personBirthDate = calendar.getTime();
        request.setPersonBirthDate(personBirthDate);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(new Date());
        when(errorFactory.buildError(eq("ERROR_CODE_12"))).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenPersonBirthDateIsNull() {
        PersonDto request = new PersonDto();
        request.setPersonBirthDate(null);

        Optional<ValidationErrorDto> errorOpt = validation.validate(request, new AgreementDto());

        assertFalse(errorOpt.isPresent());
    }
}
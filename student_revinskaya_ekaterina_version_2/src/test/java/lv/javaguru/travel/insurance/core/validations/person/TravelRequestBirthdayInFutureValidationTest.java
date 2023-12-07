package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestBirthdayInFutureValidationTest {
    @InjectMocks
    TravelRequestBirthdayInFutureValidation birthdayInFutureValidation;
    @Mock
    DateTimeUtil dateTimeUtil;
    @Mock
    AgreementDTO agreementDTO;
    @Mock
    PersonDTO personDTO;
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainsErrorBirthdayInFutureTest(){
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(personDTO.getPersonBirthDate()).thenReturn(createDate("2024.04.08"));
        when(personDTO.getPersonalCode()).thenReturn(1323123L);
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("2024.01.08"));
        when(validationErrorFactory.buildError(eq("ERROR_CODE_13"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= birthdayInFutureValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

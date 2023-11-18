package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)


public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();


    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.12.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14.12.2025"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isEqualTo(0);
    }
    @Test
    void shouldContainFourErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isEqualTo(4);
    }
    @Test
    void shouldReturnErrorWhenFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("personFirstName"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }

    @Test
    void shouldReturnErrorWhenFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("personFirstName"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }


    @Test
    void shouldReturnErrorWhenLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("personLastName"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }

    @Test
    void shouldReturnErrorWhenLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("personLastName"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }
    @Test
    void shouldReturnErrorWhenAgreementDateFromIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("agreementDateFrom"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }
    @Test
    void shouldReturnErrorWhenAgreementDateToIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("agreementDateTo"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!")
        );
    }
    @Test
    void shouldReturnErrorWhenAgreementDateToIsAfterThenAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("20.12.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("19.12.2025"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("agreementDateTo"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must not be before agreementDateFrom!")
        );

    }
    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("20.12.2020"));
        when(request.getAgreementDateTo()).thenReturn(createDate("19.12.2025"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(0).getField()).isEqualTo("agreementDateFrom"),
                () -> assertThat(errors.get(0).getMessage()).isEqualTo("Must be the future!")
        );
    }

    @Test
    public void shouldReturnErrorWhenDateToIsInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("20.12.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("19.12.2020"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertAll(
                () -> assertThat(errors.size()).isGreaterThan(0),
                () -> assertThat(errors.get(1).getField()).isEqualTo("agreementDateTo"),
                () -> assertThat(errors.get(1).getMessage()).isEqualTo("Must be the future!")
        );
    }

    private Date createDate(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}

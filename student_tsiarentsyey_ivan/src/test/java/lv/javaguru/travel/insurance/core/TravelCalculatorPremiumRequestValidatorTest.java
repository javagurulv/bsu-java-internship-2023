package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TravelCalculatorPremiumRequestValidatorTest {

    @Autowired
    TravelCalculatorPremiumRequestValidator validator;

    TravelCalculatePremiumRequest requestFirstNameError = new TravelCalculatePremiumRequest("", "last", new Date(5000), new Date(100000));
    TravelCalculatePremiumRequest requestLastNameError = new TravelCalculatePremiumRequest("first", "", new Date(5000), new Date(100000));
    TravelCalculatePremiumRequest requestAgreementDateFromError = new TravelCalculatePremiumRequest("name", "last",null, new Date(100000));

    TravelCalculatePremiumRequest requestAgreementDateToError = new TravelCalculatePremiumRequest("name", "last",new Date(500), null);
    TravelCalculatePremiumRequest requestOk = new TravelCalculatePremiumRequest("name", "last", new Date(5000), new Date(100000));


    ValidationError firstNameError = new ValidationError("personFirstName", "Must not be empty!");
    ValidationError lastNameError = new ValidationError("personLastName", "Must not be empty!");

    ValidationError agreementDateFromError = new ValidationError("agreementDateFrom", "Must not be empty!");
    ValidationError agreementDateToError = new ValidationError("agreementDateTo", "Must not be empty!");


    @Test
    void validatePersonFirstName() {
        assertThat(!validator.validate(requestFirstNameError).contains(firstNameError)).isEqualTo(true);
        assertThat(!validator.validate(requestOk).isEmpty()).isEqualTo(false);
    }
    @Test
    void validatePersonLastName() {
        assertThat(!validator.validate(requestLastNameError).contains(lastNameError)).isEqualTo(true);
        assertThat(!validator.validate(requestOk).isEmpty()).isEqualTo(false);
    }
    @Test
    void validateAgreementDateFrom() {
        assertThat(!validator.validate(requestAgreementDateFromError).contains(agreementDateFromError)).isEqualTo(true);
        assertThat(!validator.validate(requestOk).isEmpty()).isEqualTo(false);
    }

    @Test
    void validateAgreementDateTo() {
        assertThat(!validator.validate(requestAgreementDateToError).contains(agreementDateToError)).isEqualTo(true);
        assertThat(!validator.validate(requestOk).isEmpty()).isEqualTo(false);
    }




}

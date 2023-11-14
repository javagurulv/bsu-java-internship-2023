package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelCalculatePremiumRequestValidatorTest {
    private  TravelCalculatePremiumRequest request;
    private  TravelCalculatePremiumRequestValidator validator;
    private  List<ValidationError> expectedErrors;
    private  ValidationError validationFirstNameError;
    private  ValidationError validationLastNameError;
    @BeforeEach
    private  void init(){
        validator = new TravelCalculatePremiumRequestValidator();
        validationFirstNameError = new ValidationError("personFirstName", "Must not be empty!");
        validationLastNameError = new ValidationError("personLastName", "Must not be empty!");
        expectedErrors = new ArrayList<>();
    }
    @Test
    public void validateTestNotEmpty(){
        request = new TravelCalculatePremiumRequest(
                "Eugene",
                "Kroshinsky",
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestNullFirstName(){
        request = new TravelCalculatePremiumRequest(
                null,
                "Kroshinsky",
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        expectedErrors.add(validationFirstNameError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestEmptyFirstName(){
        request = new TravelCalculatePremiumRequest(
                "",
                "Kroshinsky",
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        expectedErrors.add(validationFirstNameError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestNullLastName(){
        request = new TravelCalculatePremiumRequest(
                "Eugene",
                null,
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        expectedErrors.add(validationLastNameError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestEmptyLastName(){
        request = new TravelCalculatePremiumRequest(
                "Eugene",
                "",
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        expectedErrors.add(validationLastNameError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestEmptyLastNameAndFirstName(){
        request = new TravelCalculatePremiumRequest(
                "",
                "",
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        expectedErrors.add(validationFirstNameError);
        expectedErrors.add(validationLastNameError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestNullLastNameAndFirstName(){
        request = new TravelCalculatePremiumRequest(
                null,
                null,
                new Date(2023, 1, 1),
                new Date(2023, 1, 1)
        );
        expectedErrors.add(validationFirstNameError);
        expectedErrors.add(validationLastNameError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
}

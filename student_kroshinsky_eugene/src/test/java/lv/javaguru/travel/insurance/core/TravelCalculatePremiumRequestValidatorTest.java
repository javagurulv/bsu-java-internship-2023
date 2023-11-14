package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelCalculatePremiumRequestValidatorTest {
    private  TravelCalculatePremiumRequest request;
    private  TravelCalculatePremiumRequestValidator validator;
    private  List<ValidationError> expectedErrors;
    private  ValidationError validationError;
    @BeforeEach
    private  void init(){
        validator = new TravelCalculatePremiumRequestValidator();
        request = new TravelCalculatePremiumRequest();
        validationError = new ValidationError("personFirstName", "Must not be empty!");
        expectedErrors = new ArrayList<>();
    }
    @Test
    public void validateTestNull(){

        request.setPersonFirstName(null);
        expectedErrors.add(validationError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestEmpty(){
        expectedErrors.add(validationError);
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
    @Test
    public void validateTestNotEmpty(){
        request.setPersonFirstName("Eugene");
        assertEquals(expectedErrors.size(), validator.validate(request).size());
        for (int i = 0; i < expectedErrors.size(); i++) {
            assertEquals(expectedErrors.get(i), validator.validate(request).get(i));
        }
    }
}

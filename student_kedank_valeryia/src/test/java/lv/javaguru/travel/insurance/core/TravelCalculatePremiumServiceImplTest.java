package lv.javaguru.travel.insurance.core;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void ValidatorIsGood() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Valeryia");
        request.setPersonLastName("Kedank");

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);
        assertEquals(error.size(), 0);

    }

    @Test
    public void ValidatorIsEmpty() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("");
        request.setPersonFirstName("Valeryia");

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);
        assertEquals(error.get(0).getField(),  "personLastName");
        assertEquals(error.get(0).getMessage(),  "Most not be empty!");

    }

    @Test
    public void ValidatorIsNull() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Valeryia");

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);
        assertEquals(error.get(0).getField(),  "personLastName");
        assertEquals(error.get(0).getMessage(),  "Most not be empty!");

    }

}
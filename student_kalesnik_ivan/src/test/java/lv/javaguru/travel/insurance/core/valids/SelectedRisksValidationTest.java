package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectedRisksValidationTest {
    private SelectedRisksValidation validation;

    @BeforeEach
    public void setUp() {
        validation = new SelectedRisksValidation();
    }


    @Test
    public void testExecute_WhenSelectedRisksIsNotEmpty() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setSelected_risks(Arrays.asList("TRAVEL_MEDICAL"));

        Optional<ValidationError> result = validation.validate(request);

        assertTrue(result.isEmpty());
    }
}


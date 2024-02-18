package lv.javaguru.travel.insurance.core;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import jakarta.validation.Validator;

@Component
@RequiredArgsConstructor
public class TravelCalculatePremiumRequestValidator {

    private final Validator validator;

    public void validateTravelCalculatePremiumRequest(TravelCalculatePremiumRequest request) {
        var violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}

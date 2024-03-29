package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}

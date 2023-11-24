package lv.javaguru.travel.insurance.validation.travel;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.RequestValidation;

import java.util.Optional;

public interface TravelRequestValidation extends RequestValidation<TravelCalculatePremiumRequest> {
    @Override
    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}

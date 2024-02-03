package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.util.Optional;

public interface TravelRequestValidation {
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request);
}

package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.ValidationError;

import java.util.List;
import java.util.Optional;

public interface TravelRequestValidation {
    Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);

    List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request);

}

package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {

    Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);

    List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request);

}
